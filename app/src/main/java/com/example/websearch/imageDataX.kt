package com.example.websearch

data class imageDataX(
    val _type: String,
    val totalCount: Int,
    val value: List<ImageValue>
)

data class ImageValue(
    val base64Encoding: Any,
    val height: Int,
    val imageWebSearchUrl: String,
    val name: String,
    val provider: ImageProvider,
    val thumbnail: String,
    val thumbnailHeight: Int,
    val thumbnailWidth: Int,
    val title: String,
    val url: String,
    val webpageUrl: String,
    val width: Int
)
data class ImageProvider(
    val favIcon: String,
    val favIconBase64Encoding: String,
    val name: String
)