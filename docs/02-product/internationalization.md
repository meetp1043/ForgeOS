# Internationalization

ForgeOS will be used by global engineering teams. The platform must support localization.

## Support Areas
- **Multiple Languages**: The UI and conversational interface should eventually support multiple languages (e.g., English, Spanish, Mandarin).
- **Locale-Aware Dates**: Date and time displays must respect the user's browser locale (e.g., DD/MM/YYYY vs MM/DD/YYYY).
- **Time Zones**: All agent logs and execution timestamps must be stored in UTC but displayed in the user's local time zone.
- **Currencies**: API token costs and billing must support localized currency conversion and display.
- **Formatting**: Number and comma formatting must respect the user's locale.

*Note: Internationalization is a P2 feature and will not be implemented in the MVP.*
