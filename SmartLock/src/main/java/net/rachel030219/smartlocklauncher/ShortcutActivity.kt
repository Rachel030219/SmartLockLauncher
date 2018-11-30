package net.rachel030219.smartlocklauncher

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast

import eu.chainfire.libsuperuser.Shell

class ShortcutActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getSharedPreferences("workingstatus", Context.MODE_PRIVATE).getBoolean("correctly", false)) {
            Shell.SU.run("am start -n com.google.android.gms/.trustagent.GoogleTrustAgentPersonalUnlockingSettings")
            finish()
        } else {
            if (!checkGMS(packageManager))
                Toast.makeText(this, R.string.err_gms_unavailable, Toast.LENGTH_LONG).show()
            else if (!Shell.SU.available())
                Toast.makeText(this, R.string.err_root_unavailable, Toast.LENGTH_LONG).show()
            else {
                Shell.SU.run("am start -n com.google.android.gms/.trustagent.GoogleTrustAgentPersonalUnlockingSettings")
                getSharedPreferences("workingstatus", Context.MODE_PRIVATE).edit().putBoolean("correctly", true).apply()
            }
            finish()
        }
    }

    private fun checkGMS(pm: PackageManager): Boolean {
        try {
            return pm.getApplicationInfo("com.google.android.gms", 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}
