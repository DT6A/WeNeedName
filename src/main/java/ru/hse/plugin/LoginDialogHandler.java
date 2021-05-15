package ru.hse.plugin;

import com.intellij.openapi.ui.Messages;

public class LoginDialogHandler {
    private static boolean isFuckingWindowNecessary() {
        return StorageData.getInstance().userInfo.isLoginWindowNecessary();
    }

    public static void tryLogin(String message) {
        if (!isFuckingWindowNecessary()) {
            return;
        }
        LoginDialog dialog = new LoginDialog(message);
        if (dialog.showAndGet()) {
            if (StorageData.getInstance().setUserInfo(dialog.getUserInfo())) {
                Messages.showInfoMessage("You are signed in!", "Plugin Information");
            } else {
                tryLogin("Could not sign in, try again");
            }
        }
        StorageData.getInstance().userInfo.setWindowNecessity(!dialog.doNotAskAgain());
    }

    public static void tryLogin() {
        tryLogin(null);
    }
}
