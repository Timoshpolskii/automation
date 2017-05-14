package main.java.API.Actions;

import main.java.API.Requests.UserTimeline;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static main.java.API.Support.SingletonRetrofit.getRetrofit;

public class UserTimelineActions implements BaseActions {

    private UserTimeline service = getRetrofit().create(UserTimeline.class);

    @Override
    public String getResponseBody(String screen_name) throws IOException {

        Call<ResponseBody> call = service.getUserPosts(screen_name);
        Response<ResponseBody> response = call.execute();
        String body = null;
        try {
            body = response.body().string();
        }
        catch (NullPointerException exception){
            System.out.println("Cannot get response body");
            exception.printStackTrace();
        }
        finally {
            return body;
        }
    }

    @Override
    public String getRequest(String screen_name) {

        Call<ResponseBody> call = service.getUserPosts(screen_name);
        return call.request().toString();
    }

    @Override
    public String getRequest(String status, String screen_name) {
        return null;
    }

    @Override
    public String getResponseBody(String status, String screen_name) throws IOException {
        return null;
    }
}