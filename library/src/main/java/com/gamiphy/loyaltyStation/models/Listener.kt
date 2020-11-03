package com.gamiphy.loyaltyStation.models;

/**
 * Interface definition for a callback to be invoked when the loyalty station requires login/sign up for the user.
 */
interface OnAuthTriggerListener {
    /**
     * Called when a login/signup has been clicked.
     *
     * @param isSignUp a login/signup has been clicked.
     */
    fun onAuthTrigger(isSignUp: Boolean)
}

/**
 * Interface definition for a callback to be invoked when the share button clicked on the loyalty station.
 */
interface OnShareListener {
    /**
     * Called when share button clicked.
     *
     * @param text share text
     * @param link share link
     */
    fun onShare(text: String, link: String)
}

/**
 * Interface definition for a callback to be invoked when the close button clicked on the loyalty station.
 */
interface OnCloseListener {
    /**
     * Called when close button clicked.
     *
     */
    fun onClose()
}