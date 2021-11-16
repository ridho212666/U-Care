package ib.ganz.u_care.network;

import ib.ganz.u_care.dataclass.NakesData;
import ib.ganz.u_care.dataclass.OrtuData;
import ib.ganz.u_care.dataclass.Response;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IServices {

    @POST("ortu/register")
    Observable<Response<OrtuData>> registerOrtu(@Body OrtuData ortuData);

    @POST("ortu/login")
    @FormUrlEncoded
    Observable<Response<OrtuData>> loginOrtu(
            @Field("nama_pengguna") String nama_pengguna,
            @Field("kata_kunci") String kata_kunci,
            @Field("firebase_token") String firebase_token
    );

    @POST("ortu/logout/{id}")
    Observable<Response<OrtuData>> logoutOrtu(@Path("id") String id);

    @POST("ortu/phone_verified/{id}")
    Observable<Response<OrtuData>> phoneVerifiedOrtu(@Path("id") String id);

    @POST("nakes/register")
    Observable<Response<NakesData>> registerNakes(@Body NakesData ortuData);

    @POST("nakes/login")
    @FormUrlEncoded
    Observable<Response<NakesData>> loginNakes(
            @Field("nama_pengguna") String nama_pengguna,
            @Field("kata_kunci") String kata_kunci,
            @Field("firebase_token") String firebase_token
    );

    @POST("nakes/logout/{id}")
    Observable<Response<NakesData>> logoutNakes(@Path("id") String id);

    @POST("nakes/phone_verified/{id}")
    Observable<Response<NakesData>> phoneVerifiedNakes(@Path("id") String id);

    @POST("all/login")
    @FormUrlEncoded
    Observable<Response> loginAll(
            @Field("nama_pengguna") String nama_pengguna,
            @Field("kata_kunci") String kata_kunci,
            @Field("firebase_token") String firebase_token
    );

//    @PUT("edit_profile")
//    @FormUrlEncoded
//    Observable<Response<OrtuData>> editProfile (
//            @Field("id") String id,
//            @Field("nama") String nama,
//            @Field("no_hp") String no_hp,
//            @Field("alamat") String alamat,
//            @Field("username") String username
//    );
//
//    @PUT("edit_password")
//    @FormUrlEncoded
//    Observable<Response<OrtuData>> editPassword (
//            @Field("id") String id,
//            @Field("password") String password
//    );
//
//    @GET("anak/{id}")
//    Observable<Response<AnakData>> getAnakById(
//            @Path("id") String id
//    );
//
//    @POST("anak/search")
//    @FormUrlEncoded
//    Observable<Response<List<AnakData>>> searchAnak(
//            @Field("search") String search
//    );
//
//    @POST("anak/add")
//    @FormUrlEncoded
//    Observable<Response> addAnak(
//            @Field("siswa_id") String siswa_id,
//            @Field("ortu_id") String ortu_id
//    );
//
//    @GET ("dashboard/{id}")
//    Observable<Response<List<AnakData>>> getDashboard(
//            @Path("id") String id
//    );
//
//    @GET ("transaction/list/{id}/{page}")
//    Observable<Response<List<TransaksiData>>> getTransaksi(
//            @Path("id") String id,
//            @Path("page") String page
//    );
//
//    @GET ("transaction/find/{id}")
//    Observable<Response<TransaksiData>> findTransaksi(
//            @Path("id") String id
//    );
//
//    @GET ("notification/{id}")
//    Observable<Response<List<NotificationData>>> getNotification(
//            @Path("id") String id
//    );
//
//    @GET ("notification/find/{id}")
//    Observable<Response<NotificationData>> findNotification(
//            @Path("id") String id
//    );
//
//    @GET ("last_topup/{id}")
//    Observable<Response<List<TopupData>>> getTopup(
//            @Path("id") String id
//    );
//
//    @GET ("topup/detail/{id}")
//    Observable<Response<TopupData>> findTopup(@Path("id") String id);
//
//    @GET ("spp/detail/{id}")
//    Observable<Response<TagihanData.SPP>> findSppByTrxId(@Path("id") String id);
//
//    @GET ("biaya_lain/detail/{id}")
//    Observable<Response<NotifikasiFragment.BiayaLain>> findBiayaLainByTrxId(@Path("id") String id);
//
//    @GET ("payment_methods")
//    Observable<Response<List<PaymentMethodData>>> getPaymentMethods();
//
//    @GET ("payment_request/{order_id}")
//    Observable<Response<MidtransNotification>> getPaymentRequest(@Path("order_id") String orderId);
//
//    @POST ("payment_request")
//    Observable<Response<NewPaymentRequestData>> makePaymentRequest(@Body NewPaymentRequestData body);
}
