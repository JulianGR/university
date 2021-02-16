<?php

// Exit if accessed directly
if ( ! defined( 'ABSPATH' ) ) exit;

// Filter allowed screen options from the plugin
add_filter( 'set-screen-option', 'pms_admin_set_screen_option', 20, 3 );
function pms_admin_set_screen_option( $status, $option, $value ){

    $per_page_options = array(
        'pms_members_per_page',
        'pms_payments_per_page',
        'pms_users_per_page'
    );

    if( in_array( $option, $per_page_options ) )
        return $value;

    return $status;

}

// Specific option filter since WordPress 5.4.2
// Other filters are added through the PMS_Submenu_Page class, but since the bulk add members is not a submenu page, we add this here
add_filter( 'set_screen_option_pms_users_per_page', 'pms_admin_bulk_add_members_screen_option', 20, 3 );
function pms_admin_bulk_add_members_screen_option( $status, $option, $value ){

    if( $option == 'pms_users_per_page' )
        return $value;

    return $status;

}
