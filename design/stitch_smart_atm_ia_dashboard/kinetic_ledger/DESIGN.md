---
name: Kinetic Ledger
colors:
  surface: '#101415'
  surface-dim: '#101415'
  surface-bright: '#363a3b'
  surface-container-lowest: '#0b0f10'
  surface-container-low: '#191c1e'
  surface-container: '#1d2022'
  surface-container-high: '#272a2c'
  surface-container-highest: '#323537'
  on-surface: '#e0e3e5'
  on-surface-variant: '#c5c6cd'
  inverse-surface: '#e0e3e5'
  inverse-on-surface: '#2d3133'
  outline: '#8f9097'
  outline-variant: '#44474c'
  surface-tint: '#bac7e1'
  primary: '#bac7e1'
  on-primary: '#243145'
  primary-container: '#0b192c'
  on-primary-container: '#75829a'
  inverse-primary: '#525f75'
  secondary: '#b4c5ff'
  on-secondary: '#002a78'
  secondary-container: '#0053db'
  on-secondary-container: '#cdd7ff'
  tertiary: '#4edea3'
  on-tertiary: '#003824'
  tertiary-container: '#001e11'
  on-tertiary-container: '#009466'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#d6e3fe'
  primary-fixed-dim: '#bac7e1'
  on-primary-fixed: '#0e1c2f'
  on-primary-fixed-variant: '#3a475c'
  secondary-fixed: '#dbe1ff'
  secondary-fixed-dim: '#b4c5ff'
  on-secondary-fixed: '#00174b'
  on-secondary-fixed-variant: '#003ea8'
  tertiary-fixed: '#6ffbbe'
  tertiary-fixed-dim: '#4edea3'
  on-tertiary-fixed: '#002113'
  on-tertiary-fixed-variant: '#005236'
  background: '#101415'
  on-background: '#e0e3e5'
  surface-variant: '#323537'
typography:
  display-currency:
    fontFamily: Geist
    fontSize: 48px
    fontWeight: '700'
    lineHeight: 56px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Geist
    fontSize: 32px
    fontWeight: '600'
    lineHeight: 40px
  headline-md:
    fontFamily: Geist
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  body-lg:
    fontFamily: Geist
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Geist
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  label-mono:
    fontFamily: JetBrains Mono
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.05em
  display-currency-mobile:
    fontFamily: Geist
    fontSize: 36px
    fontWeight: '700'
    lineHeight: 44px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  base: 8px
  gutter: 24px
  margin-mobile: 16px
  margin-desktop: 48px
  bento-gap: 16px
---

## Brand & Style

The design system is engineered for a high-stakes, premium investment banking environment adapted for physical touch-points. It blends the architectural precision of **Minimalism** with the depth of **Glassmorphism** to evoke a sense of "secure transparency."

The visual language communicates authority and technological superiority. It targets high-net-worth individuals and corporate users who expect an ATM experience that mirrors a luxury fintech application. The emotional response is one of absolute control, calm efficiency, and sophisticated security. Every interaction is designed to feel intentional, with heavy whitespace and a reduction of visual noise to focus on the movement of capital.

## Colors

The palette is anchored in a deep, nocturnal Navy that serves as the foundation for the "infinite depth" layout. 

- **Primary (Deep Navy):** Used exclusively for backgrounds and base-level containers to reduce eye strain in low-light environments.
- **Accent (Electric Cobalt):** Reserved for primary calls-to-action, active progress indicators, and focal points.
- **Secure State (Emerald):** Denotes successful transactions, biometric confirmation, and encrypted sessions.
- **Alert (Red):** High-saturation red used sparingly for fraud detection alerts and critical errors.
- **Neutrals:** A range of Slate and Ghost-White hues are utilized for typography. High-contrast white is reserved for financial figures to ensure immediate legibility.

## Typography

The design system utilizes **Geist** for its technical precision and neutral, modernist aesthetic. It provides the "developer-grade" clarity required for financial data. 

- **Financial Figures:** Use `display-currency` for all balance and transaction amounts. These should always utilize tabular figures (monospaced numbers) to prevent "jumping" during live updates.
- **Technical Labels:** **JetBrains Mono** is introduced for secondary metadata, such as transaction IDs, timestamps, and routing numbers, reinforcing the high-tech, systematic nature of the product.
- **Hierarchy:** Maintain a generous line height (minimum 1.5x for body text) to ensure readability on physical screens where viewing angles may vary.

## Layout & Spacing

This design system employs a **Bento-grid** philosophy. Content is organized into modular, rectangular "tiles" of varying sizes that snap to a 12-column grid.

- **ATM Touch Targets:** A strict 8px base unit is used. All interactive elements (buttons, inputs) must have a minimum height of 56px to accommodate various finger sizes and ensure WCAG 2.1 AA compliance for physical touch interfaces.
- **Bento Logic:** Grid items should use `bento-gap` (16px) for internal spacing. On desktop-class ATM screens, margins are expanded to 48px to create a "floating" interface effect in the center of the display.
- **Responsive Reflow:** On smaller vertical screens, the bento grid collapses into a single-column stack, prioritizing the "current balance" and "primary action" tiles at the top.

## Elevation & Depth

Depth is conveyed through **Tonal Layering** and **Glassmorphism**, rather than traditional drop shadows.

1.  **Base Level:** Deep Navy (#0B192C).
2.  **Container Level:** A slightly lighter Navy with a 1px interior stroke (White @ 10% opacity) to define edges.
3.  **Overlay Level:** Use a background blur (20px) with a semi-transparent Slate tint (White @ 5% opacity). This is used for modals, PIN pads, and confirmation sheets to maintain the user's context of the background bento-grid.
4.  **Active State:** Elements being touched should emit a subtle inner glow using the Electric Cobalt accent color, signaling physical engagement.

## Shapes

The shape language is "Soft-Tech." While the grid is rigid and structured, the corners are inviting and modern.

- **Default Components:** Use `rounded` (0.5rem) for buttons and input fields.
- **Bento Cards:** Use `rounded-xl` (1.5rem) for the primary containers to create a distinct modular look that feels premium and tailored.
- **Interactive Feedback:** Focus states should use a 2px offset ring in Electric Cobalt to ensure they are clearly visible for users with visual impairments.

## Components

### Buttons
- **Primary:** Solid Electric Cobalt with white text. High-contrast, no gradients.
- **Secondary:** Ghost style with a 1px Slate border and 5% white fill.
- **Critical:** Solid Red background for "Cancel" or "End Session" to ensure they are never missed.

### Bento Cards
- Each card must have a specific purpose (e.g., Quick Withdraw, Recent Activity, Market Trends).
- Use subtle 1px top-borders to simulate a light source from above.

### Input Fields
- PIN inputs and amount fields should feature "Staggered Character Blocks" where each digit sits in its own rounded container to prevent entry errors.
- Backgrounds should be darker than the container they sit in to create a "recessed" tactile feel.

### Chips & Status
- Use pill-shaped indicators for "Verified," "Encrypted," or "Pending."
- Status chips must always include an icon (Shield, Check, Clock) alongside text to ensure information is not conveyed by color alone.

### Iconography
- All icons must be 2px stroke-width line art. Avoid filled icons unless used as a notification badge.
- Use a consistent bounding box of 24x24px for all navigation icons.