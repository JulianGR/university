export const NAV_ROUTES: RouterNavItem[] = [
  {
    routerLink: '/complimentary',
    routerLabel: 'Free Rooms',
  },
  {
    routerLink: '/rooms',
    routerLabel: 'Private Rooms',
  },
  {
    routerLink: '/server',
    routerLabel: 'Your server',
  },
  {
    routerLink: '/payment',
    routerLabel: 'Tenant',
  },
];

export const UNAUTHORIZED_NAV_ROUTES: RouterNavItem[] = [
  {
    routerLink: '/login',
    routerLabel: 'Login',
  },
  {
    routerLink: '/register',
    routerLabel: 'Register',
  },
];


export interface RouterNavItem {
  routerLink: string;
  routerLabel: string;
}
