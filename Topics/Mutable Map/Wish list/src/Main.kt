fun makeMyWishList(wishList: Map<String, Int>, limit: Int): MutableMap<String, Int> {
    return wishList.filter { it.value <= limit }.toMutableMap()
}