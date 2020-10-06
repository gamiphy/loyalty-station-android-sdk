package com.gamiphy.loyaltyStation.models;

interface Listener {

    /**
     *  Called when the loyalty station requires login/sign up for the user or the login button inside bot clicked.
     *
     *  @param isSignUp : true for sign up redirection,false for login redirection.
     */
    fun onAuthTrigger(isSignUp: Boolean)
}