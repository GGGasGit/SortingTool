fun addUser(userMap: Map<String, String>, login: String, password: String): MutableMap<String, String> {
    return if (userMap.containsKey(login)) {
        println("User with this login is already registered!")
        userMap.toMutableMap()
    } else {
        val result = userMap.toMutableMap()
        result += login to password
        result
    }
}