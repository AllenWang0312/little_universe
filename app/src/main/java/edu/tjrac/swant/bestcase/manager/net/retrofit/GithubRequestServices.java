package edu.tjrac.swant.bestcase.manager.net.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by wpc on 2017/7/18.
 */

public interface GithubRequestServices {
    @GET("nasil2style")
    Call<ResponseBody> getString();
}
