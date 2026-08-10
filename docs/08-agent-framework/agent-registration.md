# Agent Registration

When a new Agent Definition is approved for creation, it must be registered with the Framework. The Registration layer enforces strict validation checks to prevent malformed or insecure agents from entering the routing pool.

## Registration Validation Checklist

The Framework must validate:
- **Unique ID**: Does this agent ID already exist?
- **Valid Role**: Does the role map to the approved `/AGENTS.md` hierarchy?
- **Valid Capabilities**: Are the requested capabilities recognized by the system?
- **Valid Permissions**: Are the requested permissions syntactically correct, and do they align with the agent's Risk Classification?
- **Valid Tools**: Do the requested tools actually exist in the Tool System?
- **Valid Model Policy**: Is the requested model supported by the Model Router?
- **Valid Context Policy**: Is the Context Engine capable of satisfying this request?
- **Valid Memory Policy**: Does this violate global memory constraints?
- **Security Classification**: Is the Risk Level appropriately set based on the requested permissions?
- **Evaluation Status**: Has this specific version payload passed the automated regression and adversarial test suites?
- **Version**: Is this a valid semantic version increment?

If any validation fails, the registration is rejected.
