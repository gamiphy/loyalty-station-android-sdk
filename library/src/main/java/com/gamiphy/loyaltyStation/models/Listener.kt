package com.gamiphy.loyaltyStation.models;

/**
 * Interface definition for a callback to be invoked when the loyalty station requires login/sign up for the user.
 */
interface OnAuthTriggerListener {
    /**
     * Called when a login/signup has been clicked.
     *
     * @param onAuthTrigger a login/signup has been clicked.
     */
    fun onAuthTrigger(isSignUp: Boolean)
}