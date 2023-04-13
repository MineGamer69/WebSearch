package com.example.websearch

data class newsDataX(
    val _type: String,
    val didUMean: String,
    val relatedSearch: List<Any>,
    val totalCount: Int,
    val value: List<NewsValue>
)

data class NewsValue(
    val body: String,
    val datePublished: String,
    val description: String,
    val id: String,
    val image: NewsImage,
    val isSafe: Boolean,
    val keywords: String,
    val language: String,
    val provider: NewsProviderX,
    val snippet: String,
    val title: String,
    val url: String
)

data class NewsProviderX(
    val favIcon: String,
    val favIconBase64Encoding: String,
    val name: String
)
data class NewsImage(
    val base64Encoding: String,
    val height: Int,
    val imageWebSearchUrl: Any,
    val name: Any,
    val provider: ProviderX,
    val thumbnail: String,
    val thumbnailHeight: Int,
    val thumbnailWidth: Int,
    val title: Any,
    val url: String,
    val webpageUrl: String,
    val width: Int
)