package com.carintis.quotesapp.destinations


import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("author")
class AuthorDetailsNavigator(
    private val manager: FragmentManager
) : Navigator<AuthorDetailsNavigator.Destination>() {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
//        val dialog = AuthorDetailsDialog()
//        dialog.arguments = args
//        dialog.show(manager, AuthorDetailsDialog.TAG)
        return null;
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return false
    }

    class Destination(authorDetailsNavigator: AuthorDetailsNavigator) :
        NavDestination(authorDetailsNavigator)


}