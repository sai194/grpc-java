package com.github.simplesteps.grpc.blog.server;


import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.proto.blog.Blog;
import com.proto.blog.BlogServiceGrpc;
import com.proto.blog.CreateBlogRequest;
import com.proto.blog.CreateBlogResponse;
import com.proto.blog.DeleteBlogRequest;
import com.proto.blog.DeleteBlogResponse;
import com.proto.blog.ListBlogRequest;
import com.proto.blog.ListBlogResponse;
import com.proto.blog.ReadBlogRequest;
import com.proto.blog.ReadBlogResponse;
import com.proto.blog.UpdateBlogRequest;
import com.proto.blog.UpdateBlogResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.bson.types.ObjectId;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {
  private MongoClient mongoClient = MongoClients.create("mongodb://root:password@localhost:27017");

  private MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");

  private MongoCollection<Document> collection = mongoDatabase.getCollection("blog");
  @Override
  public void createBlog(CreateBlogRequest request,
      StreamObserver<CreateBlogResponse> responseObserver) {
    System.out.println("In create blog server");
    Blog blog = request.getBlog();
    Document doc = new Document("author_id", blog.getAuthorId())
        .append("title", blog.getTitle())
        .append("content", blog.getContent());
    collection.insertOne(doc);
    String id = doc.getObjectId("_id").toString();
    CreateBlogResponse response = CreateBlogResponse.newBuilder()
        .setBlog(blog.toBuilder().setId(id))
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
    System.out.println("exit create blog server");
  }

  @Override
  public void readBlog(ReadBlogRequest request, StreamObserver<ReadBlogResponse> responseObserver) {
    System.out.println("In readBlog server!");
    String blogId = request.getBlogId();

    try {
      Document result = collection.find(eq("_id", new ObjectId(blogId)))
          .first();
      if (result != null) {
        Blog blog = Blog.newBuilder()
            .setTitle(result.getString("title"))
            .setContent(result.getString("content"))
            .setAuthorId(result.getString("author_id"))
            .setId(String.valueOf(result.getObjectId("_id")))
            .build();
        responseObserver.onNext(ReadBlogResponse.newBuilder()
            .setBlog(blog).build());
      } else {
        responseObserver.onError(
            Status.NOT_FOUND
                .withDescription("Blog not found " + blogId)
                .asRuntimeException()
        );
      }
    } catch (Exception e ) {
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription("Blog not found " + blogId +" "+e.getLocalizedMessage())
              .asRuntimeException()
      );
    }
    responseObserver.onCompleted();
    System.out.println("Exit readBlog server!");
  }

  @Override
  public void updateBlog(UpdateBlogRequest request,
      StreamObserver<UpdateBlogResponse> responseObserver) {
    System.out.println("in updateBlog server!");
    Blog blog = request.getBlog();
    String blogId = blog.getId();
    Document result = null;
    try {
      result = collection.find(eq("_id", new ObjectId(blogId)))
          .first();
    } catch (Exception e ) {
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription("Blog not found " + blogId +" "+e.getLocalizedMessage())
              .asRuntimeException()
      );
      responseObserver.onCompleted();
      return;
    }
    if(result != null) {
      Document update  = new Document("author_id", blog.getAuthorId())
          .append("title", blog.getTitle())
          .append("content", blog.getContent());
      collection.replaceOne(eq("_id", new ObjectId(blogId)), update);
      responseObserver.onNext(UpdateBlogResponse.newBuilder()
      .setBlog(blog.toBuilder())
      .build());
    } else {
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription("Blog not found " + blogId)
              .asRuntimeException()
      );
    }
    responseObserver.onCompleted();
    System.out.println("Exit updateBlog server!");

  }

  private Blog documentToBlog(Document document){
    return Blog.newBuilder()
        .setAuthorId(document.getString("author_id"))
        .setTitle(document.getString("title"))
        .setContent(document.getString("content"))
        .setId(document.getObjectId("_id").toString())
        .build();
  }

  @Override
  public void deleteBlog(DeleteBlogRequest request, StreamObserver<DeleteBlogResponse> responseObserver) {
    System.out.println("Received Delete Blog Request");

    String blogId = request.getBlogId();
    DeleteResult result = null;
    try {
      result = collection.deleteOne(eq("_id", new ObjectId(blogId)));
    } catch (Exception e) {
      System.out.println("Blog not found");
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription("The blog with the corresponding id was not found")
              .augmentDescription(e.getLocalizedMessage())
              .asRuntimeException()
      );
    }

    if (result.getDeletedCount() == 0) {
      System.out.println("Blog not found");
      responseObserver.onError(
          Status.NOT_FOUND
              .withDescription("The blog with the corresponding id was not found")
              .asRuntimeException()
      );
    } else {
      System.out.println("Blog was deleted");
      responseObserver.onNext(DeleteBlogResponse.newBuilder()
          .setBlogId(blogId)
          .build());

      responseObserver.onCompleted();
    }

  }

  @Override
  public void listBlog(ListBlogRequest request, StreamObserver<ListBlogResponse> responseObserver) {
    System.out.println("Received List Blog Request");

    collection.find().iterator().forEachRemaining(document -> {
      responseObserver.onNext(
          ListBlogResponse.newBuilder().setBlog(documentToBlog(document)).build()
      );
      try {
        Thread.sleep(3000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    responseObserver.onCompleted();
  }




}
