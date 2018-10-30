package com.crushcoder.kmovies.rest.retrofit;

import com.google.gson.Gson;
import com.smartmobe.kservice.data.rest.retrofit.BaseResponse;
import com.smartmobe.kservice.data.rest.retrofit.WrappedResponseBodyConverter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiResponseConverter extends Converter.Factory {
    private Gson mGson;

    public ApiResponseConverter(Gson gson) {
        mGson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return new StringResponseConverter();
        }
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Page.class) {
                return null;
            }
        }

        Type wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            @Override
            public Type getRawType() {
                return BaseResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        GsonConverterFactory factory = GsonConverterFactory.create(mGson);
        Converter<ResponseBody, ?> gsonConverter = factory.responseBodyConverter(wrappedType, annotations, retrofit);
        return new WrappedResponseBodyConverter(gsonConverter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return GsonConverterFactory.create(mGson).requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    private static class StringResponseConverter implements Converter<ResponseBody, String> {
        @Override
        public String convert(@NonNull ResponseBody value) throws IOException {
            return value.string();
        }
    }
}
