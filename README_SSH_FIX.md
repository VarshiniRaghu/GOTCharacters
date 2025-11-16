# How to Fix GitHub SSH Host Key Verification Error

## The Issue
You're encountering an SSH host key verification error when trying to push to GitHub. This happens because GitHub's RSA host key has changed, and your local SSH configuration is detecting this change as a potential security risk.

## Solution

You have two options to fix this issue:

### Option 1: Update the known_hosts file manually (Recommended)

1. Open a terminal and run the following command to remove the old GitHub key:
   ```bash
   ssh-keygen -R github.com
   ```

2. Then, add the new GitHub key by connecting to GitHub once:
   ```bash
   ssh -T git@github.com
   ```
   
   When prompted with "Are you sure you want to continue connecting?", type "yes" and press Enter.

### Option 2: Edit the known_hosts file directly

1. Open the known_hosts file in a text editor:
   ```bash
   nano ~/.ssh/known_hosts
   ```
   
2. Find and delete the line containing `github.com`.

3. Save the file and exit.

4. Try your git push command again.

## Why This Happens

GitHub occasionally updates their SSH host keys for security reasons. When this happens, your local SSH client detects the change and blocks the connection to protect you from potential man-in-the-middle attacks.

## Additional Information

If you want to verify GitHub's current SSH key fingerprints, you can check them on GitHub's official documentation:
https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/githubs-ssh-key-fingerprints

The current RSA key fingerprint for github.com should be:
`SHA256:uNiVztksCsDhcc0u9e8BujQXVUpKZIDTMczCvj3tD2s`