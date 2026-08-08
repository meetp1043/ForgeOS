# Accessibility

ForgeOS must be accessible to all developers, regardless of physical or cognitive ability.

## Accessibility Principles
- **Keyboard Navigation**: The entire dashboard, task manager, and conversation interface must be navigable via keyboard without requiring a mouse.
- **Screen Readers**: All UI components must use semantic HTML and ARIA labels. Agent status changes must be announced to screen readers.
- **Contrast**: UI elements and syntax highlighting themes must meet WCAG 2.1 AA color contrast ratios (minimum 4.5:1 for normal text).
- **Readable UI**: Support for browser-native zooming up to 200% without breaking layout. Use of clear, sans-serif typography.
- **Captions/Transcripts**: Any generated audio or video tutorials must include transcripts.
- **Reduced Motion**: Respect the `prefers-reduced-motion` OS setting by disabling unnecessary UI animations and agent visualization transitions.
- **Voice Alternatives**: The conversational interface should eventually support Speech-to-Text and Text-to-Speech input/output.
