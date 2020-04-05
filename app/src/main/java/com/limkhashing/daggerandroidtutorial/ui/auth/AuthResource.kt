package com.limkhashing.daggerandroidtutorial.ui.auth


// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
   val status: AuthStatus,
   val data: T? = null,
   val message: String? = null
) {
   class Authenticated<T>(data: T)
      : AuthResource<T>(AuthStatus.AUTHENTICATED, data)

   class Loading<T>(data: T? = null)
      : AuthResource<T>(AuthStatus.LOADING, data)

   class Error<T>(message: String, data: T? = null)
      : AuthResource<T>(AuthStatus.ERROR, data, message)

   class Logout<T>(message: String, data: T? = null)
      : AuthResource<T>(AuthStatus.NOT_AUTHENTICATED, data, message)

   enum class AuthStatus {
      AUTHENTICATED,
      ERROR,
      LOADING,
      NOT_AUTHENTICATED
   }
}

//class AuthResource<T>(
//   @field:NonNull @param:NonNull val status: AuthStatus,
//   @field:Nullable @param:Nullable val data: T,
//   @field:Nullable @param:Nullable val message: String?
//) {
//
//   companion object {
//      fun <T> Authenticated(@Nullable data: T): AuthResource<T> {
//         return AuthResource(AuthStatus.AUTHENTICATED, data, null)
//      }
//
//      fun <T> Error(@NonNull msg: String?, @Nullable data: T): AuthResource<T> {
//         return AuthResource(AuthStatus.ERROR, data, msg)
//      }
//
//      fun <T> Loading(@Nullable data: T): AuthResource<T> {
//         return AuthResource(AuthStatus.LOADING, data, null)
//      }
//
//      fun <T> Logout(): AuthResource<T?> {
//         return AuthResource<T?>(AuthStatus.NOT_AUTHENTICATED, null, null)
//      }
//   }
//
//   enum class AuthStatus {
//      AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
//   }
//}