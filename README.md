# PGPIM
A Java instant messenger style client/host application that utilizes BouncyCastle OpenPGP API to exchange public keys, encrypt the message for transfer over an open connection to be decoded by the private key.

Notes:
In the PGP world there is the concept of public key and private key.  In this case we'd like to treat the public key as more of a "protected" key so users have some greater assurance of who they are communicating with.  This means exchanging an agreed upon passphrase ahead of time which will be used to encrypt the public key prior to transmission between clients.  This added protection defends against man-in-the-middle attacks more effectively since even if they sniff out the public keys, they would still need the passphrase to use them.  

Order of operation:
1. Connect client to intended recipient.
2. Generate random passphrase to use for private/public key pair generation
	A) Encrypt the public key with agreed upon passphrase
3. Connected Clients pass their encrypted public keys to one-another.
	A) public key is decrypted
4. User B sends message addressed to User A's client after encrypting the message using User A's public key.
5. User A's client receives the encrypted message and uses their private key to decrypt the message for display.
6. The process for sending a message from A to B is the same except user A's client encrypts the message with user B's public Key and user B decrypts it with their own private key.
7. User C, who is listening on the network should only see PGP encrypted data passing back and forth.  When the clients pass their public keys to one another, these are encrypted using an agreed on passphrase prior to transmission.

Informational logs should not contain records of the decrypted data; keys used; or ip addresses.

Debug log output should be redirected to the console ONLY to mitigate accidental discovery of information from unintended parties.

