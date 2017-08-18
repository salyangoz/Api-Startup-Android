package io.salyangoz.apistartup.util;


import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import io.salyangoz.apistartup.api.ApiClientProvider;
import io.salyangoz.apistartup.model.APIError;

/**
 * Created by erkndeveci on 18/04/2017.
 */

public class ApiErrorUtil {

    public static APIError parseError(Response<?> response) {

        Converter<ResponseBody, APIError> converter = ApiClientProvider
                .getRetrofitInstance()
                .responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;

    }

}
