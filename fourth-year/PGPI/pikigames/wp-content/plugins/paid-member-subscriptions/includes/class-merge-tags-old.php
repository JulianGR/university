<?php

// Exit if accessed directly
if ( ! defined( 'ABSPATH' ) ) exit;

/*
 * Merge Tags Class contains the deafult merge tags and methods how to handle them
 *
 * @deprecated 2.2.4
 */
Class PMS_Merge_Tags{

    public function __construct() {

        /* filters on replacing merge tags */
        add_filter( 'pms_merge_tag_subscription_name', array( $this, 'pms_tag_subscription_name' ), 10, 6 );
        add_filter( 'pms_merge_tag_display_name', array( $this, 'pms_tag_display_name' ), 10, 6 );
        add_filter( 'pms_merge_tag_user_id', array( $this, 'pms_tag_user_id' ), 10, 6 );

        if ( ( defined( 'PMS_ER_VERSION' ) && version_compare( PMS_ER_VERSION, '1.1.1', '>=' ) === true ) || !defined( 'PMS_ER_VERSION' ) ) {

            add_filter( 'pms_merge_tag_subscription_status',          array( $this, 'pms_tag_subscription_status' ), 10, 6 );
            add_filter( 'pms_merge_tag_subscription_start_date',      array( $this, 'pms_tag_subscription_start_date' ), 10, 6 );
            add_filter( 'pms_merge_tag_subscription_expiration_date', array( $this, 'pms_tag_subscription_expiration_date' ), 10, 6 );
            add_filter( 'pms_merge_tag_subscription_price',           array( $this, 'pms_tag_subscription_price' ), 10, 6 );
            add_filter( 'pms_merge_tag_subscription_duration',        array( $this, 'pms_tag_subscription_duration' ), 10, 6 );
            add_filter( 'pms_merge_tag_username',                     array( $this, 'pms_tag_username' ), 10, 6 );
            add_filter( 'pms_merge_tag_first_name',                   array( $this, 'pms_tag_firstname' ), 10, 6 );
            add_filter( 'pms_merge_tag_last_name',                    array( $this, 'pms_tag_lastname' ), 10, 6 );
            add_filter( 'pms_merge_tag_user_email',                   array( $this, 'pms_tag_user_email' ), 10, 6 );
            add_filter( 'pms_merge_tag_site_name',                    array( $this, 'pms_tag_site_name' ), 10, 6 );
            add_filter( 'pms_merge_tag_site_url',                     array( $this, 'pms_tag_site_url' ), 10, 6 );

        }

        add_filter( 'pms_merge_tag_automatic_retry_message', array( $this, 'pms_tag_automatic_retry_message' ), 10, 6 );
        add_filter( 'pms_merge_tag_account_page_url', array( $this, 'pms_tag_account_page_url' ), 10 );

    }

    /**
     * Function that searches and replaces merge tags with their values
     * @param $text the text to search
     * @param $user_info used for merge tags related to the user
     * @param $subscription_plan_id used for merge tags related to the subscription plan
     * @return mixed teh text with merge tags replaced
     */
    static function pms_process_merge_tags( $text, $user_info, $subscription_plan_id, $start_date = '', $expiration_date = '', $status = '', $data = array() ){
        $merge_tags = PMS_Merge_Tags::get_merge_tags();
        if( !empty( $merge_tags ) ){
            foreach( $merge_tags as $merge_tag ){
                $text = str_replace( '{{'.$merge_tag.'}}', apply_filters( 'pms_merge_tag_'.$merge_tag, '', $user_info, $subscription_plan_id, $start_date, $expiration_date, $status, $data ), $text );
            }
        }
        return $text;
    }

    /**
     * Function that returns the available merge tags
     */
    static function get_merge_tags(){

        $available_merge_tags = array(
            'display_name',
            'subscription_name',
            'user_id',
        );

        if ( ( defined( 'PMS_ER_VERSION' ) && version_compare( PMS_ER_VERSION, '1.1.1', '>=' ) === true ) || !defined( 'PMS_ER_VERSION' ) ) {
            $other_tags = array( 'subscription_status', 'subscription_start_date', 'subscription_expiration_date', 'subscription_price', 'subscription_duration', 'first_name', 'last_name', 'username', 'user_email', 'site_name', 'site_url' );

            $available_merge_tags = array_merge( $available_merge_tags, $other_tags );
        }

        // this makes it the last one before add-ons
        $available_merge_tags[] = 'automatic_retry_message';
        $available_merge_tags[] = 'account_page_url';

        $available_merge_tags = apply_filters( 'pms_merge_tags', $available_merge_tags );

        return array_unique( $available_merge_tags );

    }

    /**
     * Replace the {{subscription_name}} tag
     */
    function pms_tag_subscription_name( $value, $user_info, $subscription_plan_id, $start_date = '', $expiration_date = '', $status = '' ) {
        if ( !empty( $subscription_plan_id ) ) {
            $subscription = pms_get_subscription_plan($subscription_plan_id);
            return $subscription->name;
        } else
            return '';
    }

    /**
     * Replace the {{display_name}} tag
     */
    function pms_tag_display_name( $value, $user_info, $subscription_plan_id, $start_date = '', $expiration_date = '', $status = '' ){
        if( !empty( $user_info->display_name ) )
            return $user_info->display_name;
        else if( !empty( $user_info->user_login ) )
            return $user_info->user_login;
        else
            return '';
    }

    function pms_tag_user_id( $value, $user_info, $subscription_plan_id, $start_date = '', $expiration_date = '', $status = '' ){
        if( !empty( $user_info->ID ) )
            return $user_info->ID;
        else
            return '';
    }

    /**
     * Replace the {{subscription_status}} tag
     */
    public function pms_tag_subscription_status( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( !empty( $subscription_plan_id ) && !empty( $user_info->ID ) ) {

            $subscription = pms_get_member_subscriptions( array( 'user_id' => $user_info->ID, 'subscription_plan_id' => $subscription_plan_id ) );

            if( isset( $subscription[0] ) && !empty( $subscription[0]->id ) )
                return $subscription[0]->status;
            else
                return __( 'abandoned', 'paid-member-subscriptions' );

        }

    }

    /**
     * Replace the {{subscription_start_date}} tag
     */
    public function pms_tag_subscription_start_date( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( !empty( $start_date ) )

            return date_i18n( get_option( 'date_format' ) . ' ' . get_option( 'time_format' ), strtotime( $start_date ) + ( get_option( 'gmt_offset' ) * HOUR_IN_SECONDS ) );

    }

    /**
     * Replace the {{subscription_expiration_date}} tag
     */
    public function pms_tag_subscription_expiration_date( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( !empty( $expiration_date ) )

            return date_i18n( get_option( 'date_format' ) . ' ' . get_option( 'time_format' ), strtotime( $expiration_date ) + ( get_option( 'gmt_offset' ) * HOUR_IN_SECONDS ) );

        // If Expiration Date is empty, return Billing Next Payment if available
        else if( isset( $user_info->ID ) ){

            $subscription = pms_get_member_subscriptions( array( 'user_id' => $user_info->ID, 'subscription_plan_id' => $subscription_plan_id ) );

            if( isset( $subscription[0] ) ){
                $subscription = $subscription[0];

                if( !empty( $subscription->billing_next_payment ) )
                    return date( get_option( 'date_format' ) . ' ' . get_option( 'time_format' ), strtotime( $subscription->billing_next_payment ) );

            }
        }

    }

    /**
     * Replace the {{subscription_price}} tag
     */
    public function pms_tag_subscription_price( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( !empty( $user_info->ID ) ) {

            $payments = pms_get_payments( array( 'user_id' => $user_info->ID ) );

            // If the website is doing cron we don't want the price of the last payment
            if ( empty( $payments ) || ( defined( 'DOING_CRON' ) && DOING_CRON ) ) {

                $subscription_plan = pms_get_subscription_plan( $subscription_plan_id );

                if ( !empty( $_POST['discount_code'] ) && !empty( $subscription_plan->price ) )
                    $amount = pms_calculate_discounted_amount( $subscription_plan->price, pms_get_discount_by_code( $_POST['discount_code'] ) );
                else
                    $amount = $subscription_plan->price;

            } else
                $amount = $payments[0]->amount;

            $currency = pms_get_active_currency();

            $price = ( $amount == 0 ) ? __( 'Free', 'paid-member-subscriptions' ) : $amount;

            return $price . ' ' . $currency;
        }

    }

    /**
     * Replace the {{subscription_duration}} tag
     */
    public function pms_tag_subscription_duration( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( !empty( $subscription_plan_id ) ) {

            if ( function_exists( 'pms_get_subscription_plan' ) ) {

                $subscription_plan = pms_get_subscription_plan( $subscription_plan_id );

                if ( $subscription_plan->duration == 0 )

                    return __( 'unlimited', 'paid-member-subscriptions' );

                else

                    return $subscription_plan->duration . ' ' . $subscription_plan->duration_unit . '(s)';
            }

        }

    }

    /**
     * Replace the {{username}} tag
     */
    public function pms_tag_username( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( is_object( $user_info ) && !empty( $user_info->user_login ) )

            return $user_info->user_login;

    }

    /**
     * Replace the {{first_name}} tag
     */
    public function pms_tag_firstname( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( is_object( $user_info ) && !empty( $user_info->ID ) ) {
            $first_name = get_user_meta( $user_info->ID, 'first_name', true );

            if ( !empty( $first_name ) )
                return $first_name;
        }

    }

    /**
     * Replace the {{last_name}} tag
     */
    public function pms_tag_lastname( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( is_object( $user_info ) && !empty( $user_info->ID ) ) {
            $last_name = get_user_meta( $user_info->ID, 'last_name', true );

            if ( !empty( $last_name ) )
                return $last_name;
        }

    }

    /**
     * Replace the {{user_email}} tag
     */
    public function pms_tag_user_email( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){

        if ( is_object( $user_info ) && !empty( $user_info->user_email ) )

            return $user_info->user_email;

    }

    /**
     * Replace the {{site_name}} tag
     */
    public function pms_tag_site_name( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){
        return html_entity_decode( get_bloginfo( 'name' ) );
    }

    /**
     * Replace the {{site_url}} tag
     */
    public function pms_tag_site_url( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $status ){
        return get_bloginfo( 'url' );
    }

    /**
     * Replace the {{automatic_retry_message}} tag
     */
    public function pms_tag_automatic_retry_message( $value, $user_info, $subscription_plan_id, $start_date, $expiration_date, $action ){

        if( !pms_is_payment_retry_enabled() )
            return $value;

        if( $action == 'payment_failed' && !empty( $subscription_plan_id ) && !empty( $user_info->ID ) ){

            $member_subscription = pms_get_member_subscriptions( array( 'user_id' => $user_info->ID, 'subscription_plan_id' => (int)$subscription_plan_id, 'number' => 1 ) );

            if( !empty( $member_subscription[0]->id ) ){
                $retry_count = pms_get_subscription_payments_retry_count( $member_subscription[0]->id );

                if( $retry_count < apply_filters( 'pms_retry_payment_count', 3 ) )
                    return sprintf( __( 'The payment will be automatically retried on %s. After %s more attempts, the subscription will remain expired.', 'paid-member-subscription' ), '<strong>' . $member_subscription[0]->billing_next_payment . '</strong>', '<strong>' . ( (int)apply_filters( 'pms_retry_payment_count', 3 ) - $retry_count ) . '</strong>' );
            }

        }

        return $value;

    }

    public function pms_tag_account_page_url(){

        $settings = get_option( 'pms_general_settings', false );

        if( empty( $settings ) || !isset( $settings['account_page'] ) || $settings['account_page'] == '-1' )
            return home_url();
        else
            return get_permalink( $settings['account_page'] );

    }

}


$merge_tags = new PMS_Merge_Tags();
