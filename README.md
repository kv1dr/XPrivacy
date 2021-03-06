XPrivacy
========

The ultimate, yet easy to use, privacy manager for Android

Index
-----

* [Description](https://github.com/M66B/XPrivacy#description)
* [Features](https://github.com/M66B/XPrivacy#features)
* [Restrictions](https://github.com/M66B/XPrivacy#restrictions)
* [Limitations](https://github.com/M66B/XPrivacy#limitations)
* [Installation](https://github.com/M66B/XPrivacy#installation)
* [Upgrading](https://github.com/M66B/XPrivacy#upgrading)
* [Usage](https://github.com/M66B/XPrivacy#usage)
* [Permissions](https://github.com/M66B/XPrivacy#permissions)
* [Frequently asked questions](https://github.com/M66B/XPrivacy#frequently-asked-questions)
* [Support](https://github.com/M66B/XPrivacy#support)
* [Changelog](https://github.com/M66B/XPrivacy#changelog)
* [Similar solutions](https://github.com/M66B/XPrivacy#similar-solutions)
* [News](https://github.com/M66B/XPrivacy#news)
* [Contributing](https://github.com/M66B/XPrivacy#contributing)
* [License](https://github.com/M66B/XPrivacy#license)

Description
-----------

XPrivacy can prevent applications from leaking privacy sensitive data.
XPrivacy can restrict the categories of data an application can access.
This is done by feeding an application with no or fake data.
There are several data categories which can be restricted, for example *contacts* or *location*.
For example, if you restrict access to contacts for an application,
this will result in sending an empty contact list to the application.
Similarly, restricting an application's access to your location
will result in a set location being sent to the application.

XPrivacy doesn't revoke (i.e. block) permissions from an application,
which means that most applications will continue to work as before and won't force close.
There are two exceptions to this, access to the internet and to external storage (typically an SD card)
is restricted by denying access (revoking permissions).
There is no other way to realize this, since these permissions are handled by Android in a special way.
Android delegates handling of these permission to the underlying Linux network/file system.
XPrivacy will fake offline (internet) and/or unmounted (storage) state,
but some applications try to access the internet/storage nevertheless, potentially resulting in crashes.

If restricting a category of data for an application results in problems for the application,
it is possible to allow access to the data category again to solve the issue.

By default, all newly installed applications will have no access to any data category at all,
to prevent a new application from leaking sensitive data right after installation.
Shortly after installing a new application,
XPrivacy will ask which data categories you want the new application to have access to.
XPrivacy comes with an application browser,
which allows you to quickly enable or disable applications' access to a particular data category
for example to view your calendar.
It is also possible to edit all data categories for one application.

To help you identify potential data leaks,
XPrivacy will monitor attempts made by all applications to access sensitive data.
XPrivacy will display a warning triangle icon as soon as data of a data category has been used.
If an application has requested Android permissions to access data in a data category,
this will be displayed with a key icon.
XPrivacy will also display if an application has internet access,
indicating that the application poses a risk of sharing the data it obtains with an external server.

XPrivacy is built using the [Xposed framework](http://forum.xda-developers.com/showthread.php?t=1574401).
XPrivacy taps into a vast number of carefully selected functions of Android through the Xposed framework.
Depending on the function, XPrivacy conditionally skips execution of the original function
(for example when an application tries to set a proximity alert)
or alters the result of the original function (for example to return an empty message list).

XPrivacy has been tested with CyanogenMod 10 and 10.1 (Android 4.1 and 4.2),
and is reported to work with any Android version 4.0, 4.1 or 4.2 variant, including stock ROMs.
Root access is needed to install the Xposed framework.
Because of a bug in the Xposed framework, XPrivacy currently needs a fixed Xposed binary,
which is provided as download for both Android version 4.0, 4.1, 4.2 and 4.3.


**XPrivacy was a lot of work, so please support this project**

Donate a few dollars for the [pro version](http://www.faircode.eu/xprivacy/)

or

buy [the pro enabler](https://play.google.com/store/apps/details?id=biz.bokhorst.xprivacy.pro) from Google play

or

[![Flattr](http://api.flattr.com/button/flattr-badge-large.png "Flattr This!")](http://flattr.com/thing/1491130/ "The ultimate, yet easy to use, privacy manager")


**Using XPrivacy is entirely at your own risk**

![Applications](https://raw.github.com/M66B/XPrivacy/master/screenshots/applications.png)
![Application](https://raw.github.com/M66B/XPrivacy/master/screenshots/application.png)
![Expert](https://raw.github.com/M66B/XPrivacy/master/screenshots/expert.png)
![Help](https://raw.github.com/M66B/XPrivacy/master/screenshots/help.png)
![Settings](https://raw.github.com/M66B/XPrivacy/master/screenshots/settings.png)
![Usage data](https://raw.github.com/M66B/XPrivacy/master/screenshots/usagedata.png)

Features
--------

* Simple to use
* No need to patch anything (no source, no [smali](https://code.google.com/p/smali/) or anything else)
* For any (stock) variant of Android version 4.0, 4.1, 4.2 or 4.3 (ICS, JellyBean)
* Newly installed applications are restricted by default
* Displays data actually used by an application
* Free and open source

Restrictions
------------

For easy usage, data is restricted by category:

* Accounts
	* return an empty account list
	* return fake account info
	* return empty authorization tokens
* Browser
	* return an empty bookmark list
	* return empty search history
* Calendar
	* return an empty calendar
* Calling
	* prevent calls from being placed
	* prevent SMS messages from being sent
	* prevent MMS messages from being sent
	* prevent data messages from being sent
* Contacts
	* return an empty contact list
		* content://com.android.contacts/data
		* content://com.android.contacts/raw_contacts
		* content://com.android.contacts/phone_lookup
		* content://com.android.contacts/profile
* Dictionary
	* return an empty user dictionary
* E-mail
	* return an empty list of accounts, e-mails, etc (provider)
* Identification
	* return a fake Android ID
	* return a fake device serial number
	* return a fake host name
	* return a fake Google services framework ID
	* return file not found for folder /proc
* Internet
	* revoke access to the internet
	* return fake disconnected state
	* return fake supplicant disconnected state
* Location
	* return a random or set location
	* return empty cell location
	* return an empty list of (neighboring) cell info
	* prevents geofences from being set
	* prevents proximity alerts from being set
	* prevents sending NMEA data to an application
	* prevent phone state from being sent to an application
		* Cell info changed
		* Cell location changed
	* prevent sending extra commands (aGPS data)
	* return an empty list of Wi-Fi scan results
	* prevents connecting to Google Play services
* Media
	* prevent recording audio (including from the microphone)
	* prevent taking pictures
	* prevent recording video
	* you will be notified if an application tries to perform any of these actions
* Messages
	* return an empty SMS/MMS message list
	* return an empty list of SMS messages stored on the SIM (ICC SMS)
	* return an empty list of voicemails
* Network
	* return fake IP's
	* return fake MAC's (network, Wi-Fi, bluetooth)
	* return fake BSSID/SSID
	* return an empty list of Wi-Fi scan results
	* return an empty list of configured Wi-Fi networks
	* return an empty list of bluetooth adapters/devices
* NFC
	* prevent receiving NDEF discovered
	* prevent receiving TAG discovered
	* prevent receiving TECH discovered
* Phone:
	* return a fake own/in/outgoing/voicemail number
	* return a fake subscriber ID (IMSI for a GSM phone)
	* return a fake phone device ID (IMEI)
	* return a empty ISIM/ISIM domain
	* return a empty IMPI/IMPU
	* return a fake MSISDN
	* return fake mobile network info
		* Country: 001 (test network)
		* Operator: 00101 (test network)
		* Operator name: fake
	* return fake SIM info
		* Country: XX
		* Operator: 00101
		* Operator name: fake
		* Serial number (ICCID): fake
	* return empty [APN](http://en.wikipedia.org/wiki/Access_Point_Name) list
	* return no currently used APN
	* return an empty call log
	* return an empty list of voicemail messages
	* prevent phone state from being sent to an application
		* Call forwarding indication
		* Call state changed (ringing, off-hook)
		* Mobile data connection state change / being used
		* Message waiting indication
		* Service state changed (service/no service)
		* Signal level changed
	* return an empty group identifier level 1
* Shell
	* Linux shell
	* Superuser shell
	* Load/library (by default not restricted)
* Storage
	* revoke permission to the [media storage](http://www.chainfire.eu/articles/113/Is_Google_blocking_apps_writing_to_SD_cards_/)
	* revoke permission to the external storage (SD card)
	* return fake unmounted state
* System
	* return an empty list of installed applications
	* return an empty list of recent tasks
	* return an empty list of running processes
	* return an empty list of running services
	* return an empty list of running tasks
	* return an empty list of widgets
	* return an empty list of applications (provider)
	* return an empty clipboard
	* prevent package add, replace, restart and remove notifications
	* prevent statusbar notifications
* View
	* prevent links from opening in the browser
	* you will be notified if an application tries to open a link

Limitations
-----------

* Android can be restricted, but there will be no usage data available (warning triangles)
* /proc cannot be restricted for Android (serial number, MAC address, etc)
* Phone number cannot be restricted for the standard phone application

Installation
------------

It seems like a lot of steps, but it is done in no time:

1. Requirements:
	* Android 4.0+ (ICS) or 4.1+ (JellyBean), check with *System Settings* > *About phone* > *Android version*
	* Custom recovery ([CWM](http://forum.xda-developers.com/wiki/ClockworkMod_Recovery), [TWRP](http://teamw.in/project/twrp2), no stock recovery)
1. **Make a backup**
1. If not done already: root your device; the procedure depends on the brand and model of your device
1. Enable *System settings* > *Security* > *Unknown sources*
1. Install the [Xposed framework](http://forum.xda-developers.com/showthread.php?t=1574401)
	* Be sure to follow **all** installation steps
	* Be sure to install the latest **version 2.1.4**
	* Download and copy the Xposed disabler to your SD card to disable Xposed in case of troubles
	* [MIUI](http://en.miui.com/) is not supported by Xposed
1. Install XPrivacy from the [Google play](https://play.google.com/store/apps/details?id=biz.bokhorst.xprivacy)
	* Alternatively download from [here](http://goo.im/devs/M66B/xprivacy)
	* Alternatively download from [here](http://d-h.st/users/M66B/?fld_id=19078#files)
1. Enable XPrivacy from the Xposed installer
1. Reboot into recovery
1. Flash the Xposed fix for your Android version from [here](http://goo.im/devs/M66B/xprivacy)
	* Android ICS 4.0.x / CM9: Xposed_fix_4.0.zip
	* Android JB 4.1.x / CM10: Xposed_fix_4.1.zip
	* Android JB 4.2.x / CM10.1: Xposed_fix_4.2.zip
	* Android JB 4.3.x / CM10.2: Xposed_fix_4.3.zip
	* **This fix is only for Xposed version 2.1.4**
	* Alternatively download from [here](http://d-h.st/users/M66B/?fld_id=19078#files)
1. Reboot

I do not recommend using XPrivacy in combination with any of the
[similar solutions](https://github.com/M66B/XPrivacy#similar-solutions),
because it will most probably result in conflicts (with as possible consequence data leakage).

If you want to uninstall XPrivacy, you have two options:

1. Disable XPrivacy in the Xposed installer
1. Uninstall Xposed using the Xposed installer

Upgrading
---------

* **Make a backup**
* **Do not remove the previous version** (else your settings will get lost)
* Download the new version
* Install the new version over the previous version
* Reboot your device

You can check for updates using the options menu.
If there is an update the browser will download it.

Usage
-----

The application starts in the main view, where at the top a data category can be selected.
By ticking one or more check boxes in the list below, the selected data category can be restricted for the chosen applications.
The default category is 'all', meaning that all data categories will be restricted.
The main view functions as a batch editor.

Tapping on an application icon shows the detailed view, where all the data categories for the selected application can be managed.
This view will also appear by tapping on the notification that appears after updating or installing an application.
By default all data categories will be restricted for new installed applications to prevent leaking privacy sensitive data from the beginning.

Data categories exist to make it easier to manage restrictions.
The data categories in the detailed view can be drilled down to individual functions.
If the category is restricted, individual functions can be allowed by clearing the function check boxes.

To see it in action: try restricting the category *Identification* for
[Android Id Info](https://play.google.com/store/apps/details?id=com.bzgames.androidid)
or try restriction the category *Contacts* for the Contacts application.

**Applying some restrictions require restarting applications and/or your device**

If an application requested Android permissions for a data category,
the category will be marked with a key icon.
If an application used/tried to use data, the data category will be marked with a warning triangle icon.
If an application has internet permissions to access a world globe icon will be shown.
These icons are just a guideline, because an application can access privacy sensitive data without Android permissions,
for example the serial number of your device
and because is not possible to monitor data usage in each and every situation,
for example not for access to the internet or the external storage.
Be aware that an application could access the internet through another (sister) application.

Enabling the internet or storage restriction means blocking access to the internet
or to the external storage (typically the SD card).
This may result in error messages and even in forced closes of the application.

Filtering:

* Tap the grayed triangle to filter applications that used the data in the selected data category
* Type in the edit box to filter applications by name (tap the cross to clear)
* Tick the check box to filter applications with the selected data category restricted

Exceptions:

* */proc* and *GservicesProvider* in the category *Identification* needs to be set manually
* *getScanResults* in the category *Location* needs to be set manually
* *load* and *loadLibrary* in the category *Shell* need to be set manually

**Using XPrivacy is entirely at your own risk**

Permissions
-----------

XPrivacy asks for the following Android permissions:

* Accounts: to select accounts to allow for applications
* Contacts: to select contacts to allow for applications
* Boot: to check if XPrivacy is enabled
* Internet: to check for updates (only manual from the menu)
* Storage: to export settings to the SD card (only [pro version](http://www.faircode.eu/xprivacy/))

If you don't like this, you can always restrict XPrivacy itself ...

Frequently asked questions
--------------------------

**(1) Will XPrivacy make my device slower?**

Maybe a little bit, but it will probably not be noticeable.

**(2) Does XPrivacy use a lot of memory or battery?**

Almost nothing.

**(3) Can you help me with rooting my device?**

There are already enough guides to help you to root your device.
Use your favorite search engine to find one.

**(4) Do I really need to install the Xposed fix?**

If you like to have all data restricted, yes.
The current Xposed version cannot hook all functions with the same name having different parameters,
like *requestLocationUpdates* of [LocationManager](http://developer.android.com/reference/android/location/LocationManager.html).

**(5) How can I reset all XPrivacy settings?**

*Manage apps* > *XPrivacy* > *Clear data*

**(6) Can I backup XPrivacy and settings?**

Yes, you can, for example with [Titanium backup](https://play.google.com/store/apps/details?id=com.keramidas.TitaniumBackup),
but you can only restore onto the same device.
Exporting/importing settings will work across devices.
To export/import settings you will need the [pro version](http://www.faircode.eu/xprivacy/).

**(10) Which functions are exactly restricted?**

Many, see [here](https://github.com/M66B/XPrivacy/blob/master/src/biz/bokhorst/xprivacy/XPrivacy.java) for all details.

**(11) What did you fix in the Xposed framework?**

See [here](https://github.com/M66B/Xposed/commit/8a46f91bfd1381f78d1deb575041f51bae5d3dda).

**(12) How safe is XPrivacy?**

Great care has been taken to develop XPrivacy, nevertheless data could leak and applications can crash,
although this is fortunately rare. So far no critical bugs have been reported.

**(14) I get 'Incompatible ...' !**

An internal check of XPrivacy failed, resulting in potential data leakage.
Please press *OK* to send me the support information, so I can look into it.

**(15) Do I need to install Xposed/the fix again when I install an update of my kernel or ROM?**

In case of a kernel update: no.

In case of a ROM update: yes.

XPrivacy will warn you when Xposed isn't installed,
the wrong version of Xposed is installed
or when the XPrivacy is not enabled in Xposed.

The right order for ROM updates is:

* Flash ROM
* Flash Google apps, when required
* Reboot
* Enable Xposed
* Reboot, flash fix

**(16) Can I restrict an application with root access?**

Yes, you can by restricting shell access.

**(17) Will restrictions be applied immediately?**

It can take up to 15 seconds before changes in restrictions will be effective, because of caching.
Changing the internet and storage restriction requires an application restart.
Please note that in many cases pressing *back*, only moves the application to the background.

**(18) Can XPrivacy ask for restrictions on demand / display a message on data usage?**

It cannot always, since it works deep within Android,
and therefore it is IMHO not a good idea to ask for restrictions when it could,
because this will probably result into confusion only.

There is a lot of privacy sensitive data processed within Android,
especially if there are a lot of applications installed.
It would slow down your device considerably if XPrivacy would notify data usage.

Newly installed applications are by default fully restricted.
Restricting an application should not result into any force closes (crashes),
please create an issue if this happens (see the support secion below),
it only means that an application cannot see the restricted data.
If an application should see the data, you can remove the associated restriction at any time.

**(19) Does XPrivacy have a firewall?**

Yes, you can restrict internet access for any application.
If you want to partly enable internet, for example for Wi-Fi only,
you will have to use a firewall application, like [AFWall+](http://forum.xda-developers.com/showthread.php?t=1957231).
The reason is that XPrivacy works within Android
and that detailed firewall rules can only be applied within the Linux kernel.

**(21) I get 'Unable to parse package'**

This means the downloaded apk is corrupt.
Try disabling your popup blocker or download using another computer.

**(22) How can I make a logcat?**

See [here](http://forum.xda-developers.com/showthread.php?t=1726238).

**(23) Where are the settings of XPrivacy stored?**

The restriction settings of XPrivacy are stored as private application data,
because the settings of XPrivacy should be considered as privacy sensitive data too.
It is possible to backup the application and the data, but you can restore it onto the same device only.
This is because Android assigns different UID's to the same applications on different devices.
Exporting settings on one device and importing settings onto another device will work,
but this requires the [pro version](http://www.faircode.eu/xprivacy/).
If you want to backup the exported settings, they are in the folder *.xprivacy* on the SD card.

**(24) Can you make possible to randomize data?**

Everything is possible, but some applications crash when feeded with random data
and it is not always possible to randomize things (like IMEI or MAC addresses).

**(25) Why doesn't undoing a data category restriction disable the function exceptions too?**

If you accidentally undo a data category restriction all the function exception would be lost.
The function exceptions only apply when the data category is restricted.

**(26) How can I export/import my settings?**

For this you need the [pro version](http://www.faircode.eu/xprivacy/).
Exported settings are stored in the folder *.xprivacy* in the file *XPrivacy.xml*.
You can copy this file to the same place on a second device.
When importing, settings are only applied to applications that exist on the second device and have exported settings.
This also applies to system apps.

**(27) How can I filter restriction categories by Android permissions?**

The default is to show only restriction categories for which an application has Android permissions.
This can be changed using the settings.
Some restriction categories don't require Android permissions, however.

**(28) I have restricted locations but my GPS status icon still appears**

That is correct, XPrivacy only replaces the real location by a fake location.
It even uses the real location to randomize the fake location.
The idea is that everything should appear as normal as possible to an application.

**(29) How about multi-user support?**

The XPrivacy engine works deep within Android and has no information about users.
Each application is identified by a *uid* and all restictions are based on this unique identifier.
Each user has his own set of applications.
Each user can manage the restrictions for the applications available to him/her.
If Android uses the same uid for an application that two users share,
both users can manage the restrictions for this application.

Support
-------

If you encounter any bug or data leakage please [report an issue](https://github.com/M66B/XPrivacy/issues),
including a [logcat](http://developer.android.com/tools/help/logcat.html)
(use [pastebin](http://pastebin.com/) or a similar service).

**Before submitting any issue please ensure you are running the latest version of XPrivacy.**

**Before submitting any issue please [read this](http://forum.xda-developers.com/showpost.php?p=43892381&postcount=1703).**

If you have a feature request, please [create an issue](https://github.com/M66B/XPrivacy/issues),
but check if there doesn´t already exist a similar request.

If you have any question or suggestion, you can leave a message in the [XDA XPrivacy forum thread](http://forum.xda-developers.com/showthread.php?p=42488236).

Changelog
---------

How to get beta releases:

* Through the Play store
	1. Become a member of [this community](https://plus.google.com/u/0/communities/111030382795741607349)
	1. Become a beta tester [here](https://play.google.com/apps/testing/biz.bokhorst.xprivacy)
* Download from [Dev-Host](http://d-h.st/users/M66B/?fld_id=19078#files)

**Next release**

* Updated French translation
* Updated Simplified Chinese translation

**Version 1.7.23 BETA!**

* Run privacy provider in separate process
* Updated Catalan translation

**Version 1.7.22 BETA!**

* Always show categories with data usage (even without permssions)
* Performance improvements (enum hooks)
* Updated Slovak translation

**Version 1.7.21 BETA!**

* Show permissions for indivudual functions
* Restructured method hooking
* Updated Portuguese translation

**Version 1.7.20 BETA!**

* Restructured meta data (restriction/function definitions)

**Version 1.7.19 BETA!**

* Restrict [BluetoothDevice](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)
* Restrict [ClipboardManager](http://developer.android.com/reference/android/content/ClipboardManager.html) (category *System*) ([issue](https://github.com/M66B/XPrivacy/issues/359))
* 4.3: restrict [NotificationListenerService](http://developer.android.com/reference/android/service/notification/NotificationListenerService.html) (category *System*) ([issue](https://github.com/M66B/XPrivacy/issues/360))
* Better fake offline state location providers
* More accurate usage data (in case of Android denying permission)
* Performance and compatibility improvements

**Version 1.7.18 BETA!**

* Option to show usage data from the application details view
* Fix usage data ([issue](https://github.com/M66B/XPrivacy/issues/364), [issue](https://github.com/M66B/XPrivacy/issues/365))
* Revert 1.7.16 change

**Version 1.7.17 BETA!**

* Contacts: also check for raw contacts ([issue](https://github.com/M66B/XPrivacy/issues/361))

**Version 1.7.16 BETA!**

* Run provider in separate process to solve heap problems

**Version 1.7.15 BETA!**

* Performance improvements (the usage data will be reset because of this, but **not** the restrictions)
* Disable Android version warning for version 4.3
* Updated telephony function permissions ([issue](http://forum.xda-developers.com/showpost.php?p=43949917&postcount=1820))
* Updated German translation
* Updated Polish translation
* Updated Russian translation
* Updated Vietnamese translation

**Version 1.7.14 BETA!**

* Request large heap

**Version 1.7.13 BETA!**

* Filter usage data by restricted or not (menu)
* Display icon if restricted in usage data view
* Menu to refresh usage data
* Less memory usage during import ([issue](https://github.com/M66B/XPrivacy/issues/329))
* Export file name with date / import with file chooser ([issue](https://github.com/M66B/XPrivacy/issues/327))
* Share exported file ([issue](https://github.com/M66B/XPrivacy/issues/337))
* Updated target SDK version to 18 (Android 4.3)
* 4.3: restrict [getGroupIdLevel1](http://developer.android.com/reference/android/telephony/TelephonyManager.html)
* 4.3: restrict [ACTION_RESPOND_VIA_MESSAGE](http://developer.android.com/reference/android/telephony/TelephonyManager.html)
* 4.3: restrict [getAccountsByTypeForPackage](http://developer.android.com/reference/android/accounts/AccountManager.html)
* Performance improvements
* Updated German translation
* Updated Japanese translation
* Updated Lithuanian translation
* Updated Simplified Chinese translation

**Version 1.7.10 BETA!**

* Open application settings when clicking in the data usage view

**Version 1.7.9 BETA!**

* Performance improvements
* Consider no Android permissions required as having Android permissions for a category
* Added data usage view for debugging purposes
* Updated Norwegian translation
* Updated Slovak translation

**Version 1.7.8 BETA!**

* Performance improvements
* Filter on internet permission
* Transparent spinner background ([issue](https://github.com/M66B/XPrivacy/issues/323))
* Attempt to fix select contacts crash (reported on XDA)
* Display uid's (as discussed on XDA)
* Added Vietnamese translation
* Updated Catalan translation
* Updated French translation
* Updated Lithuanian translation
* Updated Polish translation
* Updated Turkish translation

**Version 1.7.7**

* Restrict [Google auth](https://developer.android.com/reference/com/google/android/gms/auth/GoogleAuthUtil.html)
* Fetch accounts/contacts in the background
* Show orange triangles for function usage
* Display icon for frozen (not enabled) applications, thanks @[Looki75](http://forum.xda-developers.com/member.php?u=2468642) for the icon
* Added Lithuanian translation
* Updated Catalan translation
* Updated French translation
* Updated German translation
* Updated Japanese translation
* Updated Russian translation
* Updated Simplified Chinese translation
* Updated Slovak translation

**Version 1.7.6**

* Added disclaimer on first run
* Removed duplicate *getByName* in category *internet*

**Version 1.7.5**

* Show disabled applications when not filtering system applications
* Marked *getActiveNetworkInfo* and *getNetworkInfo* as dangerous
* Updated Italian translation

**Version 1.7.4**

* Rebuild application list on package change ([issue](https://github.com/M66B/XPrivacy/issues/226))
* Fake offline location providers (not enabled by default) ([issue](https://github.com/M66B/XPrivacy/issues/266))
* Use existing settings when installing an application again ([issue](https://github.com/M66B/XPrivacy/issues/295))
* Added Serbian translation
* Updated Dutch translation
* Updated French translation
* Updated Portuguese translation
* Updated Slovenian translation

**Version 1.7.3**

* Updated Spanish translation
* Fixed YouTube crash ([issue](https://github.com/M66B/XPrivacy/issues/297))

**Version 1.7.2**

* Better hide internet connectivity, thanks @[sorgelig](https://github.com/sorgelig)
* Fixed exporting dangerous functions ([issue](https://github.com/M66B/XPrivacy/issues/283))
* Location through Google Play services restricted (function *connect*) ([issue](https://github.com/M66B/XPrivacy/issues/233))
* Added Portuguese translation
* Updated Czech translation
* Updated Hebrew translation
* Updated Greek translation
* Updated Spanish translation

**Version 1.7**

* Restrict access to /proc (fixed again)
* Restrict package change notifications ([issue](https://github.com/M66B/XPrivacy/issues/256))
* Restrict *addGeofence* and *getLastLocation* (hidden function)
* Restrict *getWifiApConfiguration* (hidden function), thanks @[vipere](https://github.com/vipere)
* Restrict DNS in category *Internet*, thanks @[sorgelig](https://github.com/sorgelig) ([issue](https://github.com/M66B/XPrivacy/issues/132))
* Marked *location/getScanResults* as dangerous
* Higher resolution launcher icons ([source](http://openiconlibrary.sourceforge.net/gallery2/?./Icons/mimetypes/tango-style/application-pgp-signature.png))
* Sort function names
* Fixed leaking SSID on Android 4.2+
* Fixed notification icons
* Updated Catalan translation
* Updated French translation
* Updated German translation
* Updated Japanese translation
* Updated Simplified Chinese translation
* Updated Slovenian translation
* Updated Swedish translation

**Version 1.6.6**

* Reverted "Restrict access to /proc" (again)
* Updated Arabic translation
* Update French translation
* Updated Polish translation

**Version 1.6.5**

* Explicitly allow /proc for Android

**Version 1.6.4**

* User interface improvements
* Stability improvements
* Restrict access to /proc (fixed)
* Updated Catalan translation
* Updated Simplified Chinese translation
* Updated Slovak trabnslation

**Version 1.6.3**

* Fixed toggling all in main view
* Add Catalan translation

**Version 1.6.2**

* Do not apply dangerous restriction categories to new applications / all
* Add legend for colors in help
* Fixed filtering after switching theme

**Version 1.6.1**

* Reverted "Restrict access to /proc"
* Integrate clear icon into application name filter
* Updated Polish translation

**Version 1.6**

* Restrict access to /proc ([issue](https://github.com/M66B/XPrivacy/issues/227))
* New internet icon, thanks @[Looki75](http://forum.xda-developers.com/member.php?u=2468642)
* Replace expert mode by color coding, thanks @[Looki75](http://forum.xda-developers.com/member.php?u=2468642) for the colors
* Always allow drilling down in the application details view
* Fixed registering property names
* Added Swedish translation
* Updated French translation
* Updated Polish translation
* Updated Simplified Chinese translation

**Version 1.5**

* User interface improvements
* Performance improvements
* More generic matching of system properties
* Move categories internet, storage and system to expert mode ([issue](https://github.com/M66B/XPrivacy/issues/213), [issue](https://github.com/M66B/XPrivacy/issues/214))
* Setting to filter system applications ([issue](https://github.com/M66B/XPrivacy/issues/215))
* Option to set a template for new applications / application all menu ([issue](https://github.com/M66B/XPrivacy/issues/17))
* New colored icon set, thanks @[Looki75](http://forum.xda-developers.com/member.php?u=2468642) ([issue](https://github.com/M66B/XPrivacy/issues/206))
* Menu to select allowed contacts for an application ([issue](https://github.com/M66B/XPrivacy/issues/41))
* Increase length of ICCID to 20 digits ([issue](https://github.com/M66B/XPrivacy/issues/222))
* Filter categories by permission in the details view ([issue](https://github.com/M66B/XPrivacy/issues/225))
* Fixed checking for E-mail permission
* Fixed restricting recording in some situations ([issue](https://github.com/M66B/XPrivacy/issues/209))
* Fixed navigation of application detailed view ([issue](https://github.com/M66B/XPrivacy/issues/205))
* Fixed clearing export/import done notification
* Added Danish translation
* Updated Hungarian translation
* Updated Japanese translation
* Updated Norwegian translation
* Updated Simplified Chinese translation
* Updated Slovak translation
* Updated Turkish translation

**Version 1.4**

* Menu to select allowed accounts for an application
* Enable navigate up again
* Added info icon to application view
* Menu to restrict all applications ([issue](https://github.com/M66B/XPrivacy/issues/172))
* Restrict E-mail provider ([issue](https://github.com/M66B/XPrivacy/issues/199))
* Restrict application provider (category system)
* Fixed geocoding name feedback ([issue](https://github.com/M66B/XPrivacy/issues/198))
* Updated Japanese translation
* Updated Polish translation
* Updated Simplified Chinese translation

**Version 1.3**

* Dark and light holo theme, thanks @[Looki75](http://forum.xda-developers.com/member.php?u=2468642) for the icons and reviewing
* Filter on used AND name AND restricted
* Icon to clear text filter
* Display application version number in new/updated notification
* Increased maximum length of MNC to three digits ([issue](https://github.com/M66B/XPrivacy/issues/178))
* Some peformance improvements
* Fixed storage/internet restriction sometimes not working ([issue](https://github.com/M66B/XPrivacy/issues/174))
* Fixed shell commands starting with *sh* and *su*, like *show*, thanks @[Tungstwenty](https://github.com/Tungstwenty)
* Fixed stuck notification bar while exporting ([issue](https://github.com/M66B/XPrivacy/issues/170))
* Updated Arabic translation
* Updated simplified Chinese translation
* Updated Czech translation
* Updated French translation
* Updated Hungarian translation
* Updated Japanese translation
* Updated Norwegian translation
* Updated Polish translation
* Updated Slovak translation
* Updated Slovenian translation
* Updated Turkish translation

**Version 1.2**

* Setting for SIM serial# ([ICCID](http://en.wikipedia.org/wiki/ICCID#ICCID)) ([issue](https://github.com/M66B/XPrivacy/issues/167))
* Fixed possible location listener leak, thanks @[Tungstwenty](https://github.com/Tungstwenty)
* Fixed MAC address for network info
* Fixed application list leak ([issue](https://github.com/M66B/XPrivacy/issues/157))

**Version 1.1**

* *All* view (disabled check box means *some* restrictions) ([issue](https://github.com/M66B/XPrivacy/issues/154))
* Filter applications by permissions (default enabled)
* Restrict activity manager (running/recent processes/services/tasks) ([issue](https://github.com/M66B/XPrivacy/issues/157))
* Restrict app widget manager ([issue](https://github.com/M66B/XPrivacy/issues/157))
* Add Wi-Fi scan result to location category (Google Maps)
* Setting for subscriber ID ([IMSI](http://en.wikipedia.org/wiki/International_mobile_subscriber_identity)) ([issue](https://github.com/M66B/XPrivacy/issues/166))
* Filter disabled applications ([issue](https://github.com/M66B/XPrivacy/issues/165))
* Fixed switching filtering
* Fixed empty default settings (empty MCC, MNC, country, etc)
* Fixed navigate up (ICS)
* Added Hungarian translation
* Updated Polish translation
* Updated simplified Chinese translation
* Updated Slovak translation

**Version 1.0**

* Display geocoded address
* Setting for GSF ID, thanks @[vipere](https://github.com/vipere) ([issue](https://github.com/M66B/XPrivacy/issues/149))
* Settings for [MCC](https://en.wikipedia.org/wiki/Mobile_country_code), [MNC](https://en.wikipedia.org/wiki/Mobile_Network_Code) and country ([ISO 3166-1](http://en.wikipedia.org/wiki/ISO_3166-1))
* Delete existing settings before import
* Fixed switching between filtering selected/used
* Added Italian translation
* Updated Polish translation
* Updated Slovenian translation

**Version 0.43**

* Split media/sdcard storage restriction (expert only) ([issue](https://github.com/M66B/XPrivacy/issues/125))
* Move shell restriction out of expert mode, load/Library by default not restricted
* Move XPrivacy files to the folder .xprivacy ([pro version](http://www.faircode.eu/xprivacy/) only) ([issue](https://github.com/M66B/XPrivacy/issues/27))
* Fake disconnected state for internet restriction ([issue](https://github.com/M66B/XPrivacy/issues/132))
* Fake unmounted state for storage restriction (external storage only)
* Fake Google services framework ID, not restricted by default, thanks @[vipere](https://github.com/vipere) ([issue](https://github.com/M66B/XPrivacy/issues/134))
* Progress bar while loading applications, thanks @[Tungstwenty](https://github.com/Tungstwenty)
* Removed boot restriction
* Check package manager service, Wi-Fi info and activity thread for compatibility
* [Geocoding](http://en.wikipedia.org/wiki/Geocoding) of location names ([issue](https://github.com/M66B/XPrivacy/issues/139))
* Fixed internet/storage restriction for ICS ([issue](https://github.com/M66B/XPrivacy/issues/123))
* Fixed keyboard popup, thanks @[vipere](https://github.com/vipere)
* Fixed and improved fake phone data ([issue](https://github.com/M66B/XPrivacy/issues/116), see also [Restrictions](https://github.com/M66B/XPrivacy#restrictions))
* Fixed fake SSID for Android 4.2+ ([issue](https://github.com/M66B/XPrivacy/issues/116))
* Fixed rebuilding application list on orientation change
* Added Czech translation
* Added Norwegian translation
* Added Turkish translation
* Updated German translation
* Updated Japanese translation
* Updated Polish translation
* Updated Russian translation
* Updated Simplified Chinese translation
* Updated Slovak translation
* Updated Slovenian translation

**Version 0.42**

* Display notification when XPrivacy is not enabled also when Xposed installer is not present
* Better ICS compatibility ([issue](https://github.com/M66B/XPrivacy/issues/108))
* Fixed usage data for Build.SERIAL
* Fixed crash on back ([issue](https://github.com/M66B/XPrivacy/issues/112))
* Added Russian translation
* Updated French translation
* Updated German translation
* Updated Japanese translation
* Updated Polish translation
* Updated Slovenian translation

**Version 0.41**

* Filter applications by used data ([issue](https://github.com/M66B/XPrivacy/issues/79))
* Display notification when XPrivacy is not enabled in Xposed after boot ([issue](https://github.com/M66B/XPrivacy/issues/101))
* **Experimental** support for Android ICS 4.0.x
* Attempt to fix delay when applying boot restriction
* Added Polish translation
* Updated Slovak translation

**Version 0.40**

* Restrict NFC discovery
* Restrict access to (internal) media storage

**Version 0.39**

* Restrict user dictionary ([issue](https://github.com/M66B/XPrivacy/issues/89))
* Notification for updated applications (restrictions will be left alone) ([issue](https://github.com/M66B/XPrivacy/issues/96))

**Version 0.38**

* Added check box to filter restricted applications ([issue](https://github.com/M66B/XPrivacy/issues/79))
* Options to set IMEI and phone number ([issue](https://github.com/M66B/XPrivacy/issues/39))
* Use set MAC address for ro.boot.btmacaddr/ro.boot.wifimacaddr
* Removed info icon from methods
* Updated Slovak translation

**Version 0.37**

* Use fastscroll for application browser (thanks @[Tungstwenty](https://github.com/Tungstwenty))
* Application option menu *Launch*, *Settings* and *Play Store* (thanks @[Tungstwenty](https://github.com/Tungstwenty))
* Restrict LocationManager.sendExtraCommand (aGPS data)
* Info links to [wiki](http://wiki.faircode.eu/index.php?title=XPrivacy) ([issue](https://github.com/M66B/XPrivacy/issues/65))
* Fixed potential resource leak in phone state listener
* Fixed incompatible location manager message ([issue](https://github.com/M66B/XPrivacy/issues/83))
* Fixed incompatible telephony manager message ([issue](https://github.com/M66B/XPrivacy/issues/82))
* Increased thread priority to fetch app list
* Added Spanish translation
* Updated Japanese translation
* Updated Slovenian translation

**Version 0.36**

* Forgot to remove debug code

**Version 0.35**

* Speed up import ([pro version](http://www.faircode.eu/xprivacy/) only) ([issue](https://github.com/M66B/XPrivacy/issues/70))
* Remove pro menu when pro version activated
* Send support information for detected incompatibilities ([issue](https://github.com/M66B/XPrivacy/issues/82), [issue](https://github.com/M66B/XPrivacy/issues/83))
* Fixed potential account data leak ([issue](https://github.com/M66B/XPrivacy/issues/75))
* Fixed potential resource leak in location listener
* Updated German translation
* Updated simplified Chinese translation

**Version 0.34**

* Show edit icon in category browser

**Version 0.33**

* Option to switch between light/dark theme

**Version 0.32**

* Move *Shell* category to expert mode
* Export/import function restrictions ([pro version](http://www.faircode.eu/xprivacy/) only)
* Ongoing notification while exporting/importing
* Added link to restrictions in help
* Prevent application details reached from notification to show up in recent
* Added Japanese translation

**Version 0.31**

* Show storage folder in about
* Support for XPrivacy Pro from Google play
* Fixed Skype crash ([issue](https://github.com/M66B/XPrivacy/issues/50))

**Version 0.30**

* Option to set MAC address
* Workaround Skype crash ([issue](https://github.com/M66B/XPrivacy/issues/50))
* Fixed notification for uninstalled apps ([issue](https://github.com/M66B/XPrivacy/issues/51))
* Attemp to fix initial lag ([issue](https://github.com/M66B/XPrivacy/issues/59))
* Added Romanian translation

**Version 0.29**

* Fixed initial display of restrictions

**Version 0.28**

* Fixed wrong usage data in app list

**Version 0.27**

* Register more usage data (orange triangle)
* Display time of last usage (application restriction details)
* Highlight system applications (in expert mode)
* New expander symbol
* Menu *All* in application details to toggle all check boxes
* Action bar navigate up ([issue](https://github.com/M66B/XPrivacy/issues/12))
* Refresh application list when toggling expert mode
* Basic application search/filter ([issue](https://github.com/M66B/XPrivacy/issues/13))
* Added French translation
* Added Slovak translation
* Added simplified Chinese translation
* Updated Greek translation

**Version 0.26**

* Restrict process builder (shell, superuser) ([issue](https://github.com/M66B/XPrivacy/issues/44))

**Version 0.25**

* Fixed network usage data
* Fixed text wrapping application info
* Fixed to strict network restrictions (network state available now)
* Fixed to strict system restrictions (start activity available now)
* Fixed multiple installation notifications ([issue](https://github.com/M66B/XPrivacy/issues/42))
* Restrict bluetooth MAC / devices
* More compatibility checks
* Granular restrictions in expert mode only
* Hide indicator when no functions in restriction group
* Start application from application details icon
* Restrict shell commands, including superuser
* Restrict loading libraries (category shell)

**Version 0.24**

* Display application version
* More granular restrictions
* Restrict system properties:
	* ro.gsm.imei
	* net.hostname
	* ro.boot.serialno
	* ro.boot.wifimacaddr
	* ro.boot.btmacaddr
	* Let me know if there more should be restricted
* Fixed location battery drain ([issue](https://github.com/M66B/XPrivacy/issues/38))

**Version 0.23**

* Location range check
* Other location input method
* Simplified user interface
* Added Hebrew translation

**Version 0.22**

* Renamed category *Actions* to *Calling* again
* Moved opening links to new category *View*
* Export/import settings, like expert, location (export/import features [pro version](http://www.faircode.eu/xprivacy/) only)

**Version 0.21**

* Settings for latitude/longitude (Christmas Island is at latitude -10.5, longitude 105.667)
* Undo existing restrictions for apps when importing restrictions (only for apps with exported settings)

**Version 0.20**

* Consider XPrivacy as system app (for expert mode)
* Fixed category browser ([issue](https://github.com/M66B/XPrivacy/issues/30), [fix](https://github.com/M66B/XPrivacy/pull/28))
* Notify when new application installed ([feature request](https://github.com/M66B/XPrivacy/issues/10))
* Notify when new system application is installed in expert mode (default allow)

**Version 0.19**

* More environment checks when starting
* More privacy (fallback procedure when low memory)

**Version 0.18**

* Basic check for updates (manually)
* Link to [pro version](http://www.faircode.eu/xprivacy/)
* Renamed category *Calling* to *Actions* and moved opening browsers links into this category

**Version 0.17**

* User interface improvements
* Display system apps in expert mode only
* Restrict opening links ([issue](https://github.com/M66B/XPrivacy/issues/15))
* Restrict serial number (system/build properties)
* Export/import ([issue](https://github.com/M66B/XPrivacy/issues/18)) ([pro version](http://www.faircode.eu/xprivacy/) only)

**Version 0.16**

* Removed XPrivacy from *Manage apps*
* Added application list to XPrivacy

**Version 0.15**

* Restrict Android (expert mode)
* Restrict Google Service Framework (see also [limitations](https://github.com/M66B/XPrivacy#limitations))
* Restrict Wi-Fi BSSID, IP and SSID
* Restrict network info
* No default deny for updated apps

**Version 0.14**

* Restrict calling (untested)
* Restrict sending SMS
* Restrict sending MMS (untested)
* Fixed setting restrictions in the app list

**Version 0.13**

* Internet restriction (revoke permission)
* Remove restrictions/audit trail when uninstalling an app
* Custom write permission for privacy provider
* Restriction caching (Play store installed app browse should be fast again)

**Version 0.12**

* Android version check
* Check if XPrivacy is enabled
* Async app list fetch
* Several user interface improvements
* Expert mode: prevent app start
* Added Dutch/Flemish translation
* Added Bulgarian translation, thanks [borislavba](https://github.com/borislavba)
* Added German translation, thanks [NosferatuAlucard](https://github.com/NosferatuAlucard)
* Added Greek translation, thanks [mikeNG](https://github.com/mikeNG)
* Added Slovenian translation, thanks [kv1dr](https://github.com/kv1dr)

Similar solutions
-----------------

* [PDroid](http://forum.xda-developers.com/showthread.php?t=1357056)
* [PDroid 2.0](http://forum.xda-developers.com/showthread.php?t=1923576)
* [OpenPDroid](http://forum.xda-developers.com/showthread.php?t=2098156)
* [LBE Privacy Guard](https://play.google.com/store/apps/details?id=com.lbe.security.lite)
* [CyanogenMod Incognito Mode](https://plus.google.com/100275307499530023476/posts/6jzWcRR6hyu)
* [Per App Settings Module](http://forum.xda-developers.com/showthread.php?t=2072081)
* [Android 4.3+ Permission Manager](http://www.androidpolice.com/2013/07/25/app-ops-android-4-3s-hidden-app-permission-manager-control-permissions-for-individual-apps/)

The *PDroid* family provides fake or no data, more or less in the same way as XPrivacy does.
A difference is that you need to patch Android and that there is (therefore) only limited stock ROM support.
The PDroid family is open source.

*LBE Privacy Guard* revokes permissions, which will make some applications unusable.
LBE Privacy Guard also features malware protecting and data traffic control.
Some consider the closed source code of Chinese origin as a problem.

The members of the PDroid family and XPrivacy hardly use memory, but LBE Privacy Guard does.

The CyanogenMod Incognito Mode seems not to be fine grained and provides only privacy for personal data,
if the associated content provider chooses to do so.

The *Per App Settings Module* revokes permissions like LBE Privacy Guard does.
This modules offers a lot of other, intersting features.

Android 4.3+ Permission Manager is like CyanogenMod Incognito Mode.

XPrivacy can restrict more data than any of the above solutions,
including closed source applications and libraries, like Google Play services.

News
----

* [Manage Individual App Permissions with XPrivacy](http://www.xda-developers.com/android/manage-individual-app-permissions-with-xprivacy/) (June 20, 2013)
* [XPrivacy Gives You Massive Control Over What Your Installed Apps Are Allowed To Do](http://www.androidpolice.com/2013/06/23/xprivacy-gives-you-massive-control-over-what-your-installed-apps-are-allowed-to-do/) (June 23, 2013)
* [Protect Your Privacy with XPrivacy – XDA Developer TV](http://www.xda-developers.com/android/protect-your-privacy-with-xprivacy-xda-developer-tv/) (July 17, 2013)

Contributing
------------

Translations:

* Translate the strings in [this file](https://github.com/M66B/XPrivacy/blob/master/res/values/strings.xml)
* Omit lines with *translatable="false"*
* If you know how to, please create a [pull request](https://help.github.com/articles/using-pull-requests)
* Else send me the translated file [via XDA PM](http://forum.xda-developers.com/member.php?u=2799345)

Current translations:

* Bulgarian (bg)
* Catalan (ca)
* Czech (cs)
* Danish (da)
* Dutch/Flemish (nl)
* English
* French (fr)
* German (de)
* Greek (el)
* Hebrew (he/iw)
* Hungarian (hu)
* Italian (it)
* Japanese (ja)
* Lithuanian (lt)
* Norwegian (nb-rNO, nn-rNO, no-rNO)
* Polish (pl)
* Portuguese (pt)
* Romanian (ro)
* Rusian (ru)
* Serbian (sr)
* Simplified Chinese (zh-rCN)
* Slovak (sk)
* Slovenian (sl)
* Spanish (es)
* Swedish (sv)
* Turkish (tr)
* Vietnamese (vi)

Restrict new data:

* Find the package/class/method that exposes the data (look into the Android documentation/sources)
* Figure out a way to get a context (see existing code for examples)
* Create a class that extends [XHook](https://github.com/M66B/XPrivacy/blob/master/src/biz/bokhorst/xprivacy/XHook.java)
* Hook the method in [XPrivacy](https://github.com/M66B/XPrivacy/blob/master/src/biz/bokhorst/xprivacy/XPrivacy.java)
* Write a before and/or after method to restrict the data
* Do a [pull request](https://help.github.com/articles/using-pull-requests) if you want to contribute

Using Eclipse:

* Clone the GitHub project to a temporary location
* Import the GitHub project into Eclipse, copy the files
* Close Eclipse and copy the project from the temporary location over the imported project
	* Make sure you copy all hidden files
* Add the Xposed library to the build path as described [here](https://github.com/rovo89/XposedBridge/wiki/Development-tutorial#making-the-project-an-xposed-module)

Contributors do not have to donate for the [pro version](http://www.faircode.eu/xprivacy/).

License
-------

[GNU General Public License version 3](http://www.gnu.org/licenses/gpl.txt)

Copyright (c) 2013 [Marcel Bokhorst](http://blog.bokhorst.biz/about/)
([M66B](http://forum.xda-developers.com/member.php?u=2799345))

This file is part of XPrivacy.

XPrivacy is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

XPrivacy is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with XPrivacy.  If not, see [http://www.gnu.org/licenses/](http://www.gnu.org/licenses/).
