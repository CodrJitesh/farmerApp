import com.example.farmer.CommodityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommodityApi {
    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070")
    fun getCommodityPrices(
        @Query("api-key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 30,
        @Query("filters[state.keyword]") state: String? = null,
        @Query("filters[district]") district: String? = null,
        @Query("filters[market]") market: String? = null,
        @Query("filters[commodity]") commodity: String? = null
    ): Call<CommodityResponse>
}