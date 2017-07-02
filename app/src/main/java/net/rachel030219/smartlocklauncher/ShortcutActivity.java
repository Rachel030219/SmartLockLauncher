package net.rachel030219.smartlocklauncher;

import android.os.Bundle;
import android.app.Activity;

import eu.chainfire.libsuperuser.Shell;

public class ShortcutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shell.SU.run("am start -n com.google.android.gms/.trustagent.GoogleTrustAgentPersonalUnlockingSettings");
        finish();
    }
}
