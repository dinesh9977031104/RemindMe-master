package com.tekitsolutions.remindme.RestApi;

import com.tekitsolutions.remindme.Model.BankResponse;
import com.tekitsolutions.remindme.Model.CategoryResponse;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.Model.PaymentResponse;
import com.tekitsolutions.remindme.Model.ProviderResponse;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.Model.ReminderSpinnerListResponse;
import com.tekitsolutions.remindme.Model.RepeatAlarmResponse;
import com.tekitsolutions.remindme.Model.SubProviderResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    String HEADER = "Content-Type: application/json";

    @Headers(HEADER)
    @GET("category/categoryType")
    Call<List<CategoryResponse>> getCategoryList();

    @Headers(HEADER)
    @GET("provider")
    Call<List<ProviderResponse>> getProviderList();

    @Headers(HEADER)
    @GET("subprovider")
    Call<List<SubProviderResponse>> getSubProviderList();

    @Headers(HEADER)
    @GET("bank/BankType")
    Call<List<BankResponse>> getBankList();

    @Headers(HEADER)
    @GET("payment/paymentType")
    Call<List<PaymentResponse>> getPaymentList();

    @Headers(HEADER)
    @GET("remind/RemindType")
    Call<List<ReminderSpinnerListResponse>> getReminderSpinnerList();

    @Headers(HEADER)
    @GET("repeatAlarm")
    Call<List<RepeatAlarmResponse>> getRepeatAlarmList();

    @Headers(HEADER)
    @GET("reminder/{userId}")
    Call<List<Reminder>> getReminderList(@Path("userId") String userId);

    @Headers(HEADER)
    @GET("payment/{userId}")
    Call<List<ParticularPayment>> getParticularPaymentList(@Path("userId") String userId);

    @Headers(HEADER)
    @PUT("addreminder")
    Call<String> addReminder(@Body Reminder reminder);

    @Headers(HEADER)
    @DELETE("deleteRemind/{reminderId}")
    Call<String> deleteReminder(@Path("reminderId") long reminderId);

    @Headers(HEADER)
    @PUT("addPayment")
    Call<String> addPayment(@Body ParticularPayment particularPayment);

    @Headers(HEADER)
    @DELETE("deletePayment/{paymentId}")
    Call<String> deletePayment(@Path("paymentId") long paymentId);
}
