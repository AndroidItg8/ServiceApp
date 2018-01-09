package itg8.com.serviceapp.common_method;


import java.util.List;

import itg8.com.serviceapp.account.model.CustomerCareModel;
import itg8.com.serviceapp.account.model.KnowledgeModel;
import itg8.com.serviceapp.account.model.VideoModel;
import itg8.com.serviceapp.feedback.model.FeedbackListModel;
import itg8.com.serviceapp.feedback.model.FeedbackModel;
import itg8.com.serviceapp.profile.model.ProfileModel;
import itg8.com.serviceapp.showcase.model.BannerModel;
import itg8.com.serviceapp.ticket.model.TicketModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.CategoryModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProblemModel;
import itg8.com.serviceapp.ticket.model.raisedticketmodel.ProductModel;
import itg8.com.serviceapp.warranty.WarrantyModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface RetroController {


    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<ResponseBody> checkAuthentication(@Url String url,
                                           @Field("grant_type") String password,
                                           @Field("username") String emailId,
                                           @Field("password") String pswd);
    @FormUrlEncoded
    @POST()
    @Headers("Content-Type:application/x-www-form-urlencoded")

    Call<FeedbackModel> FeedBackResponsed(@Url String url , @Field("ticket_fkid")int ticketId , @Field("rating") int rating, @Field("title") String title, @Field("Description") String description);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> listOFTicket(@Url String url, @Field("type") String type);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> checkInternet(@Url String url, @Field("id") String id);

    @GET
    Call<List<ProfileModel>> getProfile(@Url String url);

    @GET
    Call<List<ProductModel>> getProductList(@Url String url);
//    product_fkid:1
//    Contactno:65354634
//    Query:dfgvdfcgfbdf

    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> sendEnquiryFormToServer(@Url String url,
                                                @Field("product_fkid") Integer groupFkid,
                                                @Field("Contactno") String number,
                                                @Field("Query") String date);


//    Invoice_fkid:1
//    AssignDate:2017/10/13
//    Description:sdfsaef


//    Invoice_fkid:1
//    AssignDate:2017/10/13
//    Description:sdfsaef
////Status:1
//    Assignedpersonname:Rahul Kale
//    AssignedContactno:dsvsdf
//    Problem_fkid:1
////OtherProblem:fdghd
//    Product_fkid:1
    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> sendRaisedTicketInfoToServer(@Url String url,
                                                     @Field("Invoice_fkid") String number,
                                                     @Field("AssignDate") String date,
                                                     @Field("Description") String deal,
                                                     @Field("Problem_fkid") int problemId,
                                                     @Field("OtherProblem") String otherProblem,
                                                     @Field("Product_fkid") int productId,
                                                     @Field("Status") int status
                                                     );

    @GET
    Call<List<CategoryModel>> getCategoryList(@Url String url);

    @GET
    Call<List<BannerModel>> getBanner(@Url String url);

    @GET
    Call<List<ProblemModel>> getProblemList(@Url String url);




    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> sendRegistrationInfoToserver(@Url String url ,@Field("compnayname") String compnayName, @Field("emailid") String email,@Field("mobileno") String mobile,@Field("FullName") String person);

    @GET
    Call<List<KnowledgeModel>> getAcckowledeAccountData(@Url String url);

    @GET
    Call<List<VideoModel>> getVideoList(@Url String url);

    @GET
    Call<List<CustomerCareModel>> getCustomerCareList(@Url String url);

    @GET
    Call<List<WarrantyModel>> getWarrantyList(@Url String url,
                                              @Query("PageNo")int page,
                                                @Query("PageSize")int size);

    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> forgetPaswd(@Url String url, @Field("Email") String email);

    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> changePasswordAfterLogin(@Url String url,@Field("NewPassword") String newpswd);

    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<FeedbackModel> changePassword(@Url String url, @Field("OldPassword") String oldpswd,@Field("NewPassword") String newpswd);

    @GET
    Call<List<FeedbackListModel>> getFeedbackList(@Url String url);

    @FormUrlEncoded
    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded")
    void sendFirebaseTokenToServer(@Url String url ,@Field("InstanceToken") String refreshedToken);

    @GET
    Call<List<TicketModel>> getOpenTicketList(@Url String url,
                                              @Query("PageNo") int page,
                                              @Query("PageSize") int limit,
                                              @Query("ST") int status);

    @GET
    Call<List<TicketModel>> getCloseTicketList(@Url String url,
                                               @Query("PageNo") int page,
                                               @Query("PageSize") int limit,
                                               @Query("ST") int status);

    @GET
    Call<List<TicketModel>> getAcceptTicketList(@Url String url,
                                                @Query("PageNo") int page,
                                                @Query("PageSize") int limit,
                                                @Query("ST") int status);
}
