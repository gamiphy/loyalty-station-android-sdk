package com.gamiphy.loyaltyStation.models;

interface AuthListener {

    /**
     *  Called when the bot requires login/sign up for the user or the login button inside bot clicked.
     *
     *  @param isSignUp : true for sign up redirection,false for login redirection.
     */
    fun onAuthTrigger(isSignUp: Boolean)
}