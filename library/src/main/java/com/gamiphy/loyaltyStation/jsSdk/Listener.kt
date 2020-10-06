package com.gamiphy.loyaltyStation.jsSdk;

interface JsListener {

    /**
     *  Called when the loyalty station requires login/sign up for the user or the login button inside bot clicked.
     *
     *  @param isSignUp : true for sign up redirection,false for login redirection.
     */
    fun onAuthTrigger(isSignUp: Boolean)
    /**
     *  Called when the loyalty station requires close.
     */
    fun onClose()
}