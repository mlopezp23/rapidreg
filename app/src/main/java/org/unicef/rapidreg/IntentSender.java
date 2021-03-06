package org.unicef.rapidreg;

import android.app.Activity;
import android.content.Intent;

import org.unicef.rapidreg.childcase.CaseActivity;
import org.unicef.rapidreg.login.LoginActivity;
import org.unicef.rapidreg.sync.SyncActivity;
import org.unicef.rapidreg.tracing.TracingActivity;

public class IntentSender {
    public static final String IS_OPEN_MENU = "is_open_menu";
    public static final String SHOW_ADD_PAGE = "show_add_page";

    public void showCasesActivity(Activity context, boolean isOpenMenu) {
        Intent intent = new Intent(context, CaseActivity.class);
        intent.putExtra(IS_OPEN_MENU, isOpenMenu);
        showActivity(context, null, intent);
    }

    public void showCaseAddPage(Activity context) {
        Intent intent = new Intent(context, CaseActivity.class);
        intent.putExtra(SHOW_ADD_PAGE, true);
        showActivity(context, null, intent);
    }

    public void showTracingActivity(Activity context) {
        showActivity(context, TracingActivity.class, null);
    }

    public void showTracingAddPage(Activity context) {
        Intent intent = new Intent(context, TracingActivity.class);
        intent.putExtra(SHOW_ADD_PAGE, true);
        showActivity(context, null, intent);
    }

    public void showLoginActivity(Activity context) {
        showActivity(context, LoginActivity.class, null);
    }

    public void showSyncActivity(Activity context) {
        showActivity(context, SyncActivity.class, null);
    }

    private void showActivity(Activity context, Class<?> cls, Intent intent) {
        if (intent == null) {
            intent = new Intent(context, cls);
        }
        context.startActivity(intent);
        context.finish();
    }
}
