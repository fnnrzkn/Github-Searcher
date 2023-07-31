package com.irfannurrizki.githubsearcher.alldata.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun findGithubUser(
        @Query(value="q", encoded=true) q: String
    ): Call<GithubApiResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun detailGithubUser(
        @Path("username") username: String
    ): Call<ItemsItem>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun findFollowersGithubUser(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun findFollowingGithubUser(
        @Path("username") username: String
    ): Call<List<ItemsItem>>
}