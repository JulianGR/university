=== Free Downloads WooCommerce ===
Contributors: squareonemedia, rwebster85
Author URI: https://squareonemedia.co.uk
Plugin URL: https://wordpress.org/plugins/download-now-for-woocommerce/
Requires at Least: 4.4
Tested up to: 5.5.1
Requires PHP: 7.0
Stable tag: trunk
License: GPLv2
License URI: https://www.gnu.org/licenses/gpl-2.0.html
Tags: woocommerce, downloads, downloadable, free downloads, download now, download no checkout, download button, download now button, wordpress, e-commerce, ecommerce

Allow users to instantly download your free digital products without going through the checkout.

== Description ==

**Free Downloads WooCommerce** is the definitive plugin for offering free downloads on your WooCommerce store. It allows users to bypass the checkout to download your free products, supports single and multiple files, works with WooCommerce Memberships, and is highly customisable.

This plugin has been designed for content creators and distributors to fully take advantage of their digital store. Whether you sell audio files, course documentation, themes and plugins, or just want to offer digital catalogues for your tangible products, **Free Downloads WooCommerce** allows your visitors to get to your free downloads with ease.

This plugin is safe and rock-solid secure, and everything is handled by your server including authentication, so you don't have to worry.

**Free Downloads WooCommerce** is also fully integrated with the official **Memberships** and **Subscriptions** plugins for WooCommerce.

== Basic Edition ==

= What you can expect in the basic free version. =

* Free digital products can be downloaded by your users without going through the checkout.
* Supports downloading products straight from the shop listings pages.
* Allow free downloading of customer owned digital products from product pages
* Custom WooCommerce Quick View feature
* Built-in support for PDF files.
* Built-in support for WooCommerce Memberships and Subscriptions, allowing you to tailor the plugin to your needs.
* Fully supports products with multiple files, with several layout options to choose from.
* Download buttons and links will automatically style to match your theme.
* Add custom CSS and HTML classes to the download buttons and links for extra visual customisation.

== Pro Edition ==

= Buy Free Downloads WooCommerce Pro today and get access to these amazing features! =

* **Advanced Product Restrictions:** Restrict free downloads by products, categories and tags.
* **Variable and Grouped Products:** Full support for grouped and variable products.
* **Multiple Download Delivery Methods:** Option to serve your downloads after redirecting to a page or emailing a link.
* **WooCommerce PDF Watermark:** Compatibility with the official WooCommerce PDF Watermark plugin.
* **Download limitations:** Restrict your users to a set number of free downloads per day/week/month/year. Users with WooCommerce Membership plans can have custom download limits, as well as specific user roles and user accounts.
* **Download tracking with reporting:** Keep a record of every free download showing the product, variation (if applicable), date, user, email address and IP address.
* **Account download history:** Show a list of the user's free download history on their WooCommerce account page.
* **Email capture:** Ask your guest users for their email address before downloading, including subscribing them to your MailChimp newsletter!
* **Paid Member Subscriptions:** Compatibility with Paid Member Subscriptions plugin by Cozmoslabs.
* **Woocommerce Products List:** Compatibility with Woocommerce Products List plugin.
* **Premium support:** You never have to worry about plugin support. We're here when you need it.
* **One-click updates:** Enjoy the simple, one-click updates that you're used to with WordPress plugins.

[Get it here](https://squareonemedia.co.uk/products/free-downloads-woocommerce/)

== How it works ==

By default any downloadable products that are free will be affected by this plugin. There is an option in the plugin settings if you would like to include paid items that are on sale for free, by default they aren't.

However, the plugin works right out of the box as it should, and only requires customising if you want to.

Rather than the *Add to Cart* button showing on product pages, site visitors will be presented with a download button, or for multiple files on a single product a set of links to each individual file will show. You can customise the experience for your visitors with several display options from links, to buttons, and even checkboxes. Once clicked the file will be securely downloaded automatically. For multiple files, the plugin dynamically creates a zip file that includes all the files for that product, and downloads that instead.

== Customisation ==

The plugin can be customised in several ways including how the download buttons or links are presented, their appearance, should users be logged in, and more. Check out the plugin settings page for everything.

== Support ==

Full supporting documentation is included with the plugin, available on the plugin settings page. There's a user guide, explanation of every setting, and FAQ with support forum links.

== Installation ==

**Manually in WordPress**

1. Download the plugin ZIP file from WordPress.org
2. From the WordPress admin dashboard go to Plugins, Add New
3. Click Upload Plugin, locate the file, upload
4. In the WordPress dashboard go to Plugins, Installed Plugins, and activate **Free Downloads WooCommerce**

**Manually using FTP**

1. Download the plugin ZIP file, extract it
2. FTP to your server and go to your root WordPress directory
3. Navigate to wp-content/plugins
4. Upload the parent directory *download-now-for-woocommerce* - the folder that contains the file som-woocommerce-download-now.php - to that location
5. In the WordPress dashboard go to Plugins, Installed Plugins, and activate **Free Downloads WooCommerce**

You can customise **Free Downloads WooCommerce** on the Plugins, Free Downloads dashboard page.

== Frequently Asked Questions ==

= What version of WooCommerce is supported? =

**Free Downloads WooCommerce** only supports WooCommerce version 3.0 and above, but should work with any version above 2.6.14.

= How can I get support? =

**Free Downloads WooCommerce** comes complete with a full guide and explanation of the plugin settings. These are available on the plugin settings page. If you need more help, please feel free to post in the [support forum](https://wordpress.org/support/plugin/download-now-for-woocommerce/).

= How are files downloaded? =

The short answer is the plugin uses a safe and secure form on the front-end which requests the file. A second round of security checks is performed, and if everything is ok the file is downloaded using the WooCommerce downloader; as well as using the download method you set for WooCommerce **(Force Downloads, X-Accel-Redirect/X-Sendfile, or Redirect)**.

= How are the dynamically created ZIP files handled? =

The product files must have been uploaded to your WordPress site, for example using the WooCommerce **Choose File** option, otherwise the ZIP file will be empty. They will not be included if they are external links.

Once created with either all of the files for a product or a selection of the files, it is temporarily saved in a folder on your server. Every hour that folder is emptied. If you deactivate this plugin, that folder and its contents will be removed.

If you use external file links it is recommended that you use the **Links Only** display method, if you have products with multiple files.

= Are the full links to files visible to a user? =

That depends on your WooCommerce settings.

If you use the **Force Downloads** or **X-Accel-Redirect/X-Sendfile** download methods (found in the WooCommerce settings, Products, Downloadable Products) for your store downloading, the file paths and URLs will be hidden. If there are multiple files downloaded as a dynamically created ZIP file, regardless of setting, the URLs will be hidden.

If you use the **Redirect** download method, the full URL may be visible for single files. For example, a PDF. This is the same as it would be without this plugin.

If in doubt and you're worried test it yourself on your own site, or please don't hesitate to [get in touch](https://wordpress.org/support/plugin/download-now-for-woocommerce/).

= Are WooCommerce Memberships and/or Subscriptions supported? =

The official Memberships and Subscriptions plugins from Woo are supported. If you have a free product that requires a user have a membership to purchase, that free product will only be available to download if the user is a member.

= What other plugins are supported? =

**Free Downloads WooCommerce** should be compatible with most plugins. If you have a problem please get in touch and we will include support if possible. Some plugins are only supported in the Pro Edition of Free Downloads WooCommerce.

Below is a list of explicitly supported plugins:
* WooCommerce Memberships
* WooCommerce Subscriptions
* TI WooCommerce Wishlist
* WooCommerce Quickview by IconicWP
* WooCommerce PDF Watermark (Pro Edition)
* Paid Member Subscriptions by Cozmoslabs (Pro Edition)
* Woocommerce Products List by NitroWeb (Pro Edition)

== Screenshots ==

1. Product with single file and 100% discount
2. Product with single file
3. Multiple files (with optional checkboxes)
4. Quick View popup with multiple files
5. Message shown when "Require login" is set for free downloads (customisable)
6. Guest email capture for downloads (Pro Edition)
7. Variable product support (Pro Edition)
8. Global download limits (Pro Edition)
9. Download limits for a WooCommerce Membership plan (Pro Edition)
10. Advanced product restrictions (Pro Edition)
11. Download tracking overview (Pro Edition)
12. Download tracking period report displaying and exporting (Pro Edition)

== Changelog ==

= 3.2.2 - 20th October 2020 =
[New] Tested with WooCommerce 4.6.0
[New] (Pro Edition) Admin tracked downloads filter can now be searched by email address used.
[Change] An admin notice, rather than an error, will now be displayed if the plugin's requirements aren't met.
[Change] Owned product downloading now includes files the user has been granted access to, such as via subscription.
[Change] (Pro Edition) Template file changes. Variation download form templates have been changed to use the $variation_id instead of $product_id when requesting a download button/link.
[Change] (Pro Edition) Updated excel export class.
[Fix] (Pro Edition) Owned variations now show the correct download button text and discounted free price.
[Fix] (Pro Edition) Variation product downloads are no longer tracked in the Free Download Logs.
[Fix] (Pro Edition) Refactored tracked downloads admin search query to prevent error logs on some systems.
[Fix] (Pro Edition) Corrected tracked downloads admin search when using IP address.

= 3.2.1 - 4th October 2020 =

* [Change] Altered scripts relating to shop page downloads to better account for dynamically created elements.

= 3.2.0 - 18th September 2020 =

* [New] Minimum required PHP version has been increased to 7.0
* [New Feature] (Pro Edition) Download limits can now be set for different user roles as well as for specific user accounts, including the ability to exclude a user role or specific user from any download limits. See the Download Limits settings page for more details.
* [New] Tested with WordPress 5.5.1
* [New] Tested with WooCommerce 4.4.1

= 3.1.93 - 17th July 2020 =

* [Fix] (Pro Edition) Corrected download limit bug with certain time zones.

= 3.1.92 - 8th July 2020 =

* [Fix] Changed CSS styling for settings page dashicon buttons to correct vertical alignment.
* [Change] Multiple checkbox download form no longer zips single selected files.
* [Change] Removed `delete_option()` call when updating the plugin as it appeared to cause issues with transients.
* [Change] (Pro Edition) Changed the `free-downloads.php` account template file to not display the download table if no download history found. Custom templates will need to be updated.
* [Change] (Pro Edition) The free downloads account table column name has been changed from "File" to "Download".
* [Change] Update procedure logic has been improved.
* [Change] Stats page can now be customised with filters to hide different parts if required.

= 3.1.91 - 14th April 2020 =

* [New] (Pro Edition) Added 3 new filters/actions: somdn_count_download_notify_email_addresses, somdn_download_type_pre_send_email, somdn_download_type_post_send_email

= 3.1.9 - 13th April 2020 =

* [Fix] (Pro Edition) Fixed bug that prevented redirect or email downloads working for multiple file link download forms
* [Fix] Removed ``somdn-download-single-form`` ID tag from download forms to prevent DOM element errors in browser. Custom templates will need to be updated
* [New] (Pro Edition) Which files a user downloaded are now included in the free download log as well as the exported Excel files
* [New] (Pro Edition) Added an additional parameter to the ``somdn_count_download_post_success`` action, $post_information
* [New] (Pro Edition) Customise how long (in seconds) the redirect download page will automatically initiate the download
* [New Feature] (Pro Edition) For site admins, set up email notifications to specific email address when new free downloads are completed
* [New] Tested with WordPress 5.4
* [New] Tested with WooCommerce 3.8.1

= 3.1.8 - 20th January 2020 =

* [Fix] (Pro Edition) Download limits not tracking correctly in some time zones
* [Fix] (Pro Edition) Fixed activation error that occurs if the free version is still active
* [New] (Pro Edition) Variations now show up for download in the user's download history account page
* [New] (Pro Edition) Added option to hide the purchased downloads section of the download history account page, when the "Show free download history on user account page" setting is enabled
* [New Feature] (Pro Edition) Added new shortcode [download_limits] to display the current user's download limits (if applicable)
* [New] Tested with WordPress 5.3.2
* [New] Tested with WooCommerce 3.8.1

= 3.1.7 - 5th June 2019 =

* [New] Added option to disable download security key checking in the General Settings / Files section
* [Change] (Pro Edition) Plugin textdomain in strings changed from constant to a string to conform to i18n
* [Change] Removed strict check when using base64_decode() to improve compatibility
* [Change] Started work on converting plugin to OOP
* [Fix] (Pro Edition) Fixed weekly download limit checks when the day of the week is set to anything other than Monday
* [Fix] (Pro Edition) Fixed download limit reached error messages not showing the correct period/frequency

= 3.1.6 - 25th March 2019 =
* [Change] Plugin textdomain in strings changed from constant to a string to conform to i18n

= 3.1.5 - 24th March 2019 =
* [New Feature] Full integrated support for WooCommerce Quickview by IconicWP
* [New Feature] External files are now downloaded to the server temporarily, meaning they can be zipped when using certain multiple files display methods
* [New Feature] (Pro Edition) New download delivery methods. Option to serve your downloads after redirecting to a page or emailing a link
* [New Feature] (Pro Edition) New display type for email capture form checkbox: Required Checkbox with Text. Form cannot be submitted until box is checked. Error message can be customised
* [New] Added a new error logging system to catch some errors and important info, viewable in the Settings -> Support -> Error Logs section. Can be copied, exported, or deleted from the settings page. Debug Logs can also be enabled for info
* [New] Plugin now includes functions to update the site database or plugin files where necessary, when updating to new versions of this plugin
* [New] Checkbox form error when no boxes have been selected for download can now be customised. Default is still "Please select at least 1 checkbox". As such both checkbox form templates have been updated
* [New] Plugin now checks for dependencies and displays a notice in WordPress admin if any are missing.
* [New] Updated POT file with new strings (new translations will be required)
* [New] Added the ability for users to add their own translations if they put them in 'wp-content/languages/free-downloads/'. These will take priority over any others
* [New] (Pro Edition) Pro Edition now includes the following localisations: British English (default), US English, Spanish and Colombian Spanish (thanks to Carlos M).
* [Fix] Fixed invalid legacy somdn_is_product_valid() function calls
* [Fix] Cleaned up erroneous or bad translatable strings
* [Fix] Corrected error that meant WordPress hosted localisations would not load in the free version
* [Fix] (Pro Edition) Fixed incorrect download checks on account page
* [Fix] (Pro Edition) MailChimp subscription error catching improved to give more detail
* [Fix] (Pro Edition) Fixed error in variation download form template for showing (Multiple Files) "Button + Filenames"
* [Change] Frontend free download checks are now only performed once per page load, making use of global variables to store data temporarily. This should improve performance on large sites
* [Change] "target" link tag now supported in custom text areas that allow links
* [Change] Plugin temporary uploads folder structure changed, as well as added empty index.php files to these directories to improve security
* [Change] Stored get_template_directory() value when building plugin templates to prevent duplicate unnecessary calls
* [Change] (Pro Edition) Version number parity between free version and Pro Edition
* [Change] (Pro Edition) Added wp_nonce check for log exporting as well as changed init action
* [Change] (Pro Edition) EDD_SL_Plugin_Updater class updated to 1.6.18
* [Change] (Pro Edition) Changes made to email capture form template. Strings adjusted, "checked" values added to inputs where needed, and incorporated new display options
* [Change] (Pro Edition) Converted default download limit messages to translatable strings

= 3.1.4 - 12th December 2018 =
* Change: Frontend free download checks are now only performed once per page load, making use of global variables to store data temporarily. This should improve performance on large sites.

= 3.1.3 - 19th November 2018 =
* Change: Action for outputting download button on product pages reverted to previous setting "woocommerce_single_product_summary". This can now also be filtered and changed manually. Filter for the action is "somdn_product_page_content_woo", priority is "somdn_product_page_content_woo_priority". See "somdn-woo-functions.php" function somdn_load_product_page_content_woo().
* Change: somdn_product_page() function now accepts an array of arguments instead of having them all as function parameters. Defaults included in the function and merged with the received $args array.

= 3.1.2 - 9th November 2018 =
* New Feature: Custom templates. You can now override the download templates in your theme. Create a new folder inside your theme directory called "somdn-templates", and follow the same template folder/file structure as found in the plugin's "Templates" folders.
* New Option: Single file products can now show the filename instead of download text.
* WooCommerce change: The default "add_to_cart" WooCommerce shortcode will now output a free download button if the product is applicable.
* Fix: Quick View feature should now support more product filter plugins.
* Fix: "That was not supposed to happen" error has been removed, and a new custom security check has been implemented to be more compatible with site cache plugins. New errors will display on the product page for any download errors.
* General cleanups and optimisations.

= 3.1.1 - 17th September 2018 =
* Corrected text domain

= 3.1.0 - 16th September 2018 =
* New Option/Feature: Quick View product popup window from shop listing pages
* New Option/Feature: Paid products your customer already owns can now be downloaded free from the product page, if you enable this option, essentially preventing repeat purchasing. The display can also be customised
* Bug Fix: Checkbox download form "Select All" button now works properly, including fixed CSS classes
* Various general cleanups and optimisations

= 3.0.96 - 17th April 2018 =
* Activating the basic or pro edition will deactivate the other if present
* Removed the legacy Custom Functions file
* Fixed bug where files were being temporarily created without downloading
* Changed download action from on_init to wp_loaded for better compatibility

= 3.0.95 - 11th April 2018 =
* Changed the script for downloading from archive pages to improve support for some themes/plugins

= 3.0.94 - 26th March 2018 =
* Fixed bug where sale items were being included regardless of plugin setting

= 3.0.93 - 2nd March 2018 =
* Fixed bug with Memberships discounts

= 3.0.92 - 28th February 2018 =
* Fixed bug that caused some filters not to work correctly
* Multiple file checkbox form now behaves more logically

= 3.0.91 - 27th February 2018 =
* Change to error displayed if ZipArchive is not install. Now shows in Multiple File plugin setting page

= 3.0.9 - 27th February 2018 =
* Refactored all code for better compatibility, support and performance
* General housekeeping

= 3.0.8 - 10th February 2018 =
* Added basic compatibility with WooCommerce versions below 3.0 (version 2.6.14 and above). If using those versions of WooCommerce, use with caution
* General housekeeping

= 3.0.7 - 7th February 2018 =
* Switched to using WooCommerce function get_file_download_path for better plugin compatibility. Other plugins hook into that filter
* General housekeeping

= 3.0.6 - 4th February 2018 =
* Changed translation filename
* Included translation file for English (GB)

= 3.0.5 - 3rd February 2018 =
* Renamed plugin to Free Downloads WooCommerce
* New Option: Hide the "read more" button on archive pages if the user could download the product if they were logged in or needed a membership
* Fixed missing translatable strings
* Cleaned up some code
* General housekeeping

= 3.0.4 - 20th January 2018 =
* Fixed php error for compatibility with Memberships

= 3.0.3 - 19th January 2018 =
* New Option: Display a message on a product page if the product is free, requires login to download for free, but the user is not logged in

= 3.0.2 - 14th January 2018 =
* Now backwards compatible with WooCommerce Memberships 1.8

= 3.0.1 - 07/01/2018 =
* Changed shortcode logic to improve support with some themes
* Cleaned up depreciated WooCommerce Membership functions
* Cleaned up php errors

= 3.0 - 02/01/2018 =
* New Option: Show file download counts on product shop page
* New Option: Restrict free downloading to specific WooCommerce Membership plans
* Introduced framework for Pro version - go to the Pro Edition settings tab to learn more
* General housekeeping

= 2.4.95 - 15/12/2017 =
* Included POT file. Plugin should now be translation ready.

= 2.4.94 - 15/12/2017 =
* Preparation for plugin internationalisation

= 2.4.93 - 12/12/2017 =
* New Option: Force ZIP file creation for single files

= 2.4.92 - 21/11/2017 =
* Fixed bug on user account memberships page
* Download buttond now add/remove a "loading" class when clicked
* General housekeeping

= 2.4.91 - 18/10/2017 =
* Removed outdated get_product() function calls, replaced with wc_get_product()

= 2.4.9 - 16/10/2017 =
* Fixed compatibility for WooCommerce Memberships 1.9+

= 2.4.8 - 10/10/2017 =
* Fixed bug with "Links Only" multiple file download option

= 2.4.7 - 09/10/2017 =
* Changed download form actions to make plugin more compatible with security features found in some plugins/themes

= 2.4.6 - 03/10/2017 =
* Fixed bug with displaying button text on shop pages

= 2.4.5 - 01/10/2017 =
* Renamed plugin to Free Downloads - WooCommerce
* New Option: add custom CSS classes to the download buttons and links
* Error message now displays if "download all" zip file is empty, usually caused by using external links
* General housekeeping

= 2.4.4 - 12/04/2017 =
* Changes for compatibility with WooCommerce version 3.0+
* Plugin now only supports WooCommerce version 3.0 and above

= 2.4.3 - 16/03/2017 =
* Fixed error when user has no memberships in the membership site

= 2.4.2 =
* Now supports WooCommerce Memberships version 1.7+

= 2.4.1 =
* Fixed bug where Membership download would fail on some setups

= 2.4 =
* Membership items with a 100% discount for members can now be included. This option is off by default

= 2.3.82 =
* Change to hide cart CSS logic

= 2.3.81 =
* Fixed plugin activation error

= 2.3.8 =
* Ouput CSS to hide the cart when download button shows, to support more plugins that may conflict.
* Added new actions to plugin activation and deactivation

= 2.3.7 =
* Fixed detection of WooCommerce Memberships on some setups

= 2.3.6 =
* WooCommerce Memberships - New settings for extra customisation
* Rewritten some functions as actions and filters
* Changed hard-coded front-end text strings to translatable functions
* General housekeeping

= 2.3.5 =
* Plugin settings now feature as an admin menu section, not plugin menu
* New feature: reset product free download count
* Code changes to use of wp_upload_dir() to fix rare PHP error

= 2.3.4 =
* Fixed bug in 2.3.3

= 2.3.3 =
* 2 new shortcodes added, one to display a free download link and another to display the free download cart content like a single product page.
* General housekeeping

= 2.3.2 =
* Fixed bug when using the [product_page id=""] WooCommerce shortcode
* Changed archive page output to anchor links instead of buttons
* General housekeeping

= 2.3.1 =
* Minor change to script loading to correct rare PHP error

= 2.3 =
* New Option: Allow download from shop / archive pages
* Added the before/after form/button WooCommerce hooks to the download buttons
* Fixed bug introduced in version 2.2 preventing download when using links
* Fixed variations being properly excluded

= 2.2 =
* Download counts now visible on products page columns (admin area)
* New support section called Features

= 2.1 =
* PDF Viewer feature. PDF files will be previewed instead of downloaded (optional)
* Individual products can now be selected, instead of globally affecting all
* Free download count now included on each product page
* General housekeeping

= 2.0 =
* Rebuilt from the ground up to be better than ever before
* Overhauled download procedure, now uses secure WooCommerce download methods
* File paths are hidden, everything is handled securely by the server
* New options pages for a tonne of customisations
* Now supports WooCommerce Memberships and Subscriptions
* Fully supports products with multiple files, several display options available
* Multiple files are wrapped up in a dynamically created ZIP file, and downloaded instantly
* Full supporting documentation built right into the plugin

= 1.11 =
* No longer conflicts with variations

= 1.1 =
* Bug fixes

= 1.0 =
* Initial release
