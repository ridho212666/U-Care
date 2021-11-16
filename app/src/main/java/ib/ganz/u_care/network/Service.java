package ib.ganz.u_care.network;

import java.util.List;

import ib.ganz.u_care.dataclass.NakesData;
import ib.ganz.u_care.dataclass.OrtuData;
import ib.ganz.u_care.dataclass.Response;
import ib.ganz.u_care.manager.SessionManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Service {

    private static IServices iService;

    private static IServices getServices() {
        if (iService == null) iService = ApiClient.getRetrofit().create(IServices.class);
        return iService;
    }

    public static Observable<Response<OrtuData>> registerOrtu(OrtuData ortu) {
        return getServices()
                .registerOrtu(ortu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<OrtuData>> loginOrtu(String nama_pengguna, String kata_kunci) {
        return getServices()
                .loginOrtu(nama_pengguna, kata_kunci, SessionManager.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<OrtuData>> logoutOrtu() {
        return getServices()
                .logoutOrtu(SessionManager.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<OrtuData>> phoneVerifiedOrtu(String id) {
        return getServices()
                .phoneVerifiedOrtu(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<NakesData>> registerNakes(NakesData ortu) {
        return getServices()
                .registerNakes(ortu)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<NakesData>> loginNakes(String nama_pengguna, String kata_kunci) {
        return getServices()
                .loginNakes(nama_pengguna, kata_kunci, SessionManager.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<NakesData>> logoutNakes() {
        return getServices()
                .logoutNakes(SessionManager.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response<NakesData>> phoneVerifiedNakes(String id) {
        return getServices()
                .phoneVerifiedNakes(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Response> login(String nama_pengguna, String kata_kunci) {
        return getServices()
                .loginAll(nama_pengguna, kata_kunci, SessionManager.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static Observable<Response<OrtuData>> logout() {
//        return getServices()
//                .logout(SessionManager.getOrtu().getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<OrtuData>> createUser(String nama, String username, String password, String no_hp) {
//        return getServices()
//                .createUser(nama, username, password, no_hp, SessionManager.getToken())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<OrtuData>> editProfile(String nama, String no_hp, String alamat, String username) {
//        return getServices()
//                .editProfile(SessionManager.getOrtu().getId(), nama, no_hp, alamat, username)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<OrtuData>> editPassword(String password) {
//        return getServices()
//                .editPassword(SessionManager.getOrtu().getId(), password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<AnakData>> getAnakById(String id) {
//        return getServices()
//                .getAnakById(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<AnakData>>> searchAnak(String search) {
//        return getServices()
//                .searchAnak(search)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response> addAnak(String siswa_id, String ortu_id) {
//        return getServices()
//                .addAnak(siswa_id, ortu_id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<AnakData>>> getDashboard(String id) {
//        return getServices()
//                .getDashboard(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<TransaksiData>>> getTransaksi(String id, int page) {
//        return getServices()
//                .getTransaksi(id, page + "")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<TransaksiData>> findTransaksi(String id) {
//        return getServices()
//                .findTransaksi(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<NotificationData>>> getNotification() {
//        return getServices()
//                .getNotification(SessionManager.getOrtu().getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<NotificationData>> findNotification(String id) {
//        return getServices()
//                .findNotification(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<TopupData>>> getTopup() {
//        return getServices()
//                .getTopup(SessionManager.getOrtu().getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<TopupData>> findTopup(String id) {
//        return getServices()
//                .findTopup(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<TagihanData.SPP>> findSppByTrxId(String id) {
//        return getServices()
//                .findSppByTrxId(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<NotifikasiFragment.BiayaLain>> findBiayaLainByTrxId(String id) {
//        return getServices()
//                .findBiayaLainByTrxId(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<List<PaymentMethodData>>> getPaymentMethods() {
//        return getServices()
//                .getPaymentMethods()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<MidtransNotification>> getPaymentRequest(String orderId) {
//        return getServices()
//                .getPaymentRequest(orderId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public static Observable<Response<NewPaymentRequestData>> makePaymentRequest(NewPaymentRequestData r) {
//        return getServices()
//                .makePaymentRequest(r)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
