data class RepoResult<T>(val status: Status, var data: T?, val message: String) {
    companion object {
        fun <T> success(msg: String, data: T?): RepoResult<T> {
            return RepoResult(Status.SUCCESS, data, msg)
        }

        fun <T> error(msg: String, data: T?): RepoResult<T> {
            return RepoResult(Status.ERROR, data, msg)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
}