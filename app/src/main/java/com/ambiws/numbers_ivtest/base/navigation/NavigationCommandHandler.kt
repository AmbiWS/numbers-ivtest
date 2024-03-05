package com.ambiws.numbers_ivtest.base.navigation

import android.app.Activity
import androidx.navigation.NavController
import com.ambiws.numbers_ivtest.utils.extensions.hideKeyboard
import com.ambiws.numbers_ivtest.utils.loge


typealias NavControllerDefinition = () -> NavController

open class NavigationCommandHandler(
    private val navControllerDefinition: NavControllerDefinition
) : NavigationCommandHandlerInterface {

    override fun handle(
        activity: Activity,
        navigationCommand: NavigationCommand
    ) {
        if (navigationCommand.hideKeyboard) {
            activity.hideKeyboard()
        }
        try {
            val navController = navControllerDefinition.invoke()
            handle(activity, navController, navigationCommand)
        } catch (e: IllegalArgumentException) {
            loge("Error happened while trying to dispatch navigation command, $e")
        }
    }

    private fun handle(
        activity: Activity,
        navController: NavController,
        navigationCommand: NavigationCommand
    ) {
        when (navigationCommand) {
            is NavigationCommand.ToUri -> {
                navController.navigate(
                    navigationCommand.uri,
                    navigationCommand.navOptions
                )
            }
            is NavigationCommand.To -> {
                navController.navigate(
                    navigationCommand.direction.actionId,
                    navigationCommand.direction.arguments,
                    navigationCommand.navOptions,
                    navigationCommand.navigationExtras
                )
            }
            is NavigationCommand.CompoundNavigationCommand -> {
                for (command in navigationCommand.navigationCommandList) {
                    handle(activity, navController, command)
                }
            }
            is NavigationCommand.Back -> {
                navController.navigateUp()
            }
            is NavigationCommand.BackToStart -> {
                navController.popBackStack(navController.graph.startDestinationId, false)
            }
        }
    }
}

interface NavigationCommandHandlerInterface {
    fun handle(
        activity: Activity,
        navigationCommand: NavigationCommand
    )
}
