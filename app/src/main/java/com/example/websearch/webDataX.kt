package com.example.websearch

data class webDataX(
    val _type: String,
    val didUMean: String,
    val relatedSearch: List<String>,
    val totalCount: Int,
    val value: List<Value>
)
data class Value(
    val body: String,
    val datePublished: String,
    val description: String,
    val id: String,
    val image: Image,
    val isSafe: Boolean,
    val keywords: String,
    val language: String,
    val provider: ProviderX,
    val snippet: String,
    val title: String,
    val url: String
)
data class ProviderX(
    val favIcon: String,
    val favIconBase64Encoding: String,
    val name: String
)
data class Image(
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