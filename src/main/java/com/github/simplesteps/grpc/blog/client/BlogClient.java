package com.github.simplesteps.grpc.blog.client;

import com.proto.blog.Blog;
import com.proto.blog.BlogServiceGrpc;
import com.proto.blog.CreateBlogRequest;
import com.proto.blog.CreateBlogResponse;
import com.proto.blog.DeleteBlogRequest;
import com.proto.blog.DeleteBlogResponse;
import com.proto.blog.ListBlogRequest;
import com.proto.blog.ReadBlogRequest;
import com.proto.blog.ReadBlogResponse;
import com.proto.blog.UpdateBlogRequest;
import com.proto.blog.UpdateBlogResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BlogClient {

  public static void main(String[] args) {
    System.out.println("Grpc Client blog!");
    BlogClient main = new BlogClient();
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
        .usePlaintext()
        .build();
    //main.createBlog(channel);
    //main.readBlog(channel);
    //main.updateBlog(channel);
    //main.deleteBlog(channel);
    main.listBlog(channel);
  }

  private void createBlog(ManagedChannel channel) {
    BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);
    Blog blog = Blog.newBuilder()
        .setAuthorId("Sai").setContent("WooHoo! First blog").setTitle("First Blog")
        .build();
    CreateBlogResponse createBlogResponse = blogClient.createBlog(CreateBlogRequest.newBuilder().
        setBlog(blog)
        .build());
    blog = createBlogResponse.getBlog();
    System.out.println("blog response "+ blog.toString());
  }

  private void readBlog(ManagedChannel channel) {
    BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);
    ReadBlogResponse readBlogResponse = blogClient.readBlog(ReadBlogRequest.newBuilder()
        .setBlogId("5e81026facdfe05161a1c639").build());
    System.out.println("blog "+ readBlogResponse.getBlog());
  }

  private void updateBlog(ManagedChannel channel) {
    BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);
    Blog blog = Blog.newBuilder()
        .setId("5e81026facdfe05161a1c632").setAuthorId("Sai").setContent("Second blog").setTitle("Second Blog")
        .build();
    UpdateBlogResponse updateBlogResponse = blogClient.updateBlog(
        UpdateBlogRequest.newBuilder().
        setBlog(blog)
        .build());
    blog = updateBlogResponse.getBlog();
    System.out.println("blog response "+ blog.toString());
  }

  private void deleteBlog(ManagedChannel channel) {
    System.out.println("Deleting blog");
    BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);
    DeleteBlogResponse deleteBlogResponse = blogClient.deleteBlog(
        DeleteBlogRequest.newBuilder().setBlogId("blogId").build()
    );

    System.out.println("Deleted blog");

  }

  private void listBlog(ManagedChannel channel) {
    BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);
    blogClient.listBlog(ListBlogRequest.newBuilder().build()).forEachRemaining(
        listBlogResponse -> System.out.println(listBlogResponse.getBlog().toString())
    );
  }
}
