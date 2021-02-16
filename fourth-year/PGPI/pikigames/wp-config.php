<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'pikigames' );

/** MySQL database username */
define( 'DB_USER', 'root' );

/** MySQL database password */
define( 'DB_PASSWORD', '' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '~egD&vzU8F^h=b2/|^]asY~&Dk^s <jOEJ9p?:7cPA`0auu[beiv1L@&ie&E8jwM' );
define( 'SECURE_AUTH_KEY',  '7;U[53+BE^HTTP^g!04y^Ps3$rw<w|@rKhDm@IoYQlqd<@$JbyB<nQg@P5|$(p`O' );
define( 'LOGGED_IN_KEY',    'gDH.BS$0~72j56[nqgeO+.v<:36)>tLayG{y%/Z]4_oTlJ-$2X!D u0L]6qgD;z>' );
define( 'NONCE_KEY',        'FG5+>9?F_$S0)`Ok6|+Xd+J=j1$&JR*S>oJLZ>&b]chYAEz0K:gBV38*O~4uIlxe' );
define( 'AUTH_SALT',        'nm)eydB:K$wr5x=bD&n[s#*C9`U-,Hpl@.7^I41Sx!7%:7RI6^winzwAW%UN2$GG' );
define( 'SECURE_AUTH_SALT', '?}C>gK&Y;heLSX8h.jnNAdw|y}35$JXqaH2dN:7|wK3.9qmF/WI&*sJcyWh)Z=mS' );
define( 'LOGGED_IN_SALT',   ',!=o.Z</,en4;]qVy{?6n:L&V(DP,dA%*TW8oL&9U@e~e98{V?pUL& >if`=}3(j' );
define( 'NONCE_SALT',       '}~H6,c}+K=oUOd!fP}bvDCXlW0m,l~3iB`x4VZ~EBSa144Shj0~)O@ScQ~tc98J8' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
