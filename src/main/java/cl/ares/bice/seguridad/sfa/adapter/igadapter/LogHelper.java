package cl.ares.bice.seguridad.sfa.adapter.igadapter;

import cl.ares.bice.ws.utiles.LoggerUtil;

import com.entrust.identityGuard.authenticationManagement.wsv9.AuthenticationFault;
import com.entrust.identityGuard.authenticationManagement.wsv9.IPLocation;
import com.entrust.identityGuard.authenticationManagement.wsv9.NameValue;
import com.entrust.identityGuard.authenticationManagement.wsv9.SharedSecret;
import com.entrust.identityGuard.userManagement.wsv9.ActivationInfo;
import com.entrust.identityGuard.userManagement.wsv9.CardUsageThresholdIndicator;
import com.entrust.identityGuard.userManagement.wsv9.ContactInfo;
import com.entrust.identityGuard.userManagement.wsv9.ExpectedLocation;
import com.entrust.identityGuard.userManagement.wsv9.Grid;
import com.entrust.identityGuard.userManagement.wsv9.GridCell;
import com.entrust.identityGuard.userManagement.wsv9.IPListEntry;
import com.entrust.identityGuard.userManagement.wsv9.MachineSecretInfo;
import com.entrust.identityGuard.userManagement.wsv9.TokenInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserCardInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserLocation;
import com.entrust.identityGuard.userManagement.wsv9.UserOTPInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserPINInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserPVNInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserPasswordInfo;
import com.entrust.identityGuard.userManagement.wsv9.UserTokenInfo;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class LogHelper {

    private static final Logger log = LoggerUtil.getLoggerInput(LogHelper.class);

    public static void logFault(Logger logger, AuthenticationFault e) {
        logger.debug("Error occurred: " +
                e.getErrorCode().toString() + " - [" +
                e.getInternalCode() + "] " +
                e.getErrorMessage());
    }
    
    public static void dumpRespuestaDesafio(Log logger, String[] respDesafioArr) {
        String msg = ("RespuestaDesafioArr[");
        for (int i = 0; i < respDesafioArr.length; i++) {
            if(i>0) msg +=(",");
            String s = respDesafioArr[i];
            msg +=("'" + s + "'");
        }
        msg +=("]");
        logger.debug(msg);
    }

    public static void dumpUser(UserInfo userinfo) {
        {
            if (userinfo == null) {
                log.debug("Null user");
            } else {
                log.debug("User:      " + userinfo.getUserid());
                log.debug("Name:      " + userinfo.getUserName());
                log.debug("Group:     " + userinfo.getGroup());
                log.debug("State:     " + userinfo.getUserState().getValue());
                log.debug("Suspend Reason: " + userinfo.getSuspendReason());
                if ((userinfo.getAliases() != null) &&
                        (userinfo.getAliases().length > 0)) {
                    log.debug("Aliases:   " + userinfo.getAliases()[0]);
                    for (int i = 1; i < userinfo.getAliases().length; i++) {
                        log.debug("           " + userinfo.getAliases()[i]);
                    }
                } else {
                    log.debug("Aliases:   ");
                }
                log.debug("Full Name: " + userinfo.getFullName());

                if (userinfo.isBadData()) {
                    log.debug("User has bad data");
                } else {
                    dumpPIN(userinfo.getPIN());
                    dumpCards(userinfo.getCards());
                    dumpOTPs(userinfo.getOTPs());
                    dumpPVN(userinfo.getPVN());
                    dumpContactInfoList(userinfo.getContactInfoList());
                    dumpPassword(userinfo.getPassword());
                    dumpTokens(userinfo.getTokens());
                    dumpLockoutInfo(userinfo);
                    dumpSharedSecrets(userinfo.getSharedSecrets());
                    dumpQASecrets(userinfo.getQaSecrets());
                    dumpAuthenticationSecrets(userinfo.getAuthenticationSecrets());
                    dumpMachineSecrets(userinfo.getMachineSecrets());
                    if (userinfo.isUsePolicyForMaxLocationHistorySize()) {
                        log.debug("Max. Location Size: " + "Policy");
                    } else {
                        log.debug("Max. Location Size: " + userinfo.getMaxLocationHistorySize());
                    }
                    if (userinfo.isUsePolicyForCheckVelocity()) {
                        log.debug("Check Velocity:     " + "Policy");
                    } else {
                        log.debug("Check Velocity:     " + userinfo.getCheckVelocity());
                    }
                    if (userinfo.isUsePolicyForCheckIPAddressInLocationHistory()) {
                        log.debug("Check IP Address In Location History:   " + "Policy");
                    } else {
                        log.debug(
                                "Check IP Address In Location History:   " + userinfo.getCheckIPAddressInLocationHistory());
                    }
                    dumpUserLocations(userinfo.getUserLocations());
                    log.debug("Use Policy For Expected Locations: " +
                            userinfo.isUsePolicyForExpectedLocations());
                    if (!userinfo.isUsePolicyForExpectedLocations()) {
                        log.debug("Expected Locations:                " + expectedLocationsToString(userinfo.getExpectedLocations()));
                    }
                    ActivationInfo ai = userinfo.getActivationInfo();
                    if (ai != null) {
                        String x = new String("");
                        if (ai.isNotSet()) {
                            x = "Not Set";
                        } else if (ai.isActivated()) {
                            x = "Activated";
                        } else {
                            Calendar y = ai.getExpireDate();
                            if (y != null) {
                                x = dateFormat(y, "");
                            }
                        }
                        log.debug("Activation Expiry Date:       " + x);
                    }
                    log.debug("OTP Allowed:                  " + userinfo.isOTPAllowed());
                    if (userinfo.isUsePolicyForOTPDeliveryEnabled()) {
                        log.debug("OTP Delivery Enabled:         " + "Policy");
                    } else {
                        log.debug("OTP Delivery Enabled:         " + userinfo.getOTPDeliveryEnabled());
                    }
                }
            }
        }

    }

    static private void dumpUserLocation(UserLocation location)
    {
       if (location == null)
       {
          log.debug("No User Location.");
       }
       else
       {
          log.debug("User Location:");
          log.debug("   Private Address: " +
                             location.isPrivateAddress());
          log.debug("   IP Address:      " + location.getIPAddress());
          if (! location.isPrivateAddress())
          {
             log.debug("   Country:         " + location.getCountry());
             log.debug("   Country Name:    " +
                                unstrip(location.getCountryName()));
             log.debug("   Region:          " + location.getRegion());
             log.debug("   Region Name:     " +
                                unstrip(location.getRegionName()));
             log.debug("   City:            " + location.getCity());
             log.debug("   ISP:             " + location.getISP());
             log.debug("   Latitude:        " + location.getLatitude());
             log.debug("   Longitude:       " + location.getLongitude());
          }
          log.debug("   Last Auth:       " + dateFormat(location.getLastAuthentication(), "not set"));
          log.debug("   Expiry Date:     " + dateFormat(location.getExpiryDate(), "not set"));
          log.debug("   Num. Auths:      " + location.getNumAuthentications());
       }
    }

    static private void dumpIPLocation(IPLocation location)
    {
       if (location == null)
       {
          log.debug("No IP Location.");
       }
       else
       {
          log.debug("IP Location:");
          log.debug("   Private Address: " +
                             location.isPrivateAddress());
          log.debug("   IP Address:      " + location.getIPAddress());
          if (! location.isPrivateAddress())
          {
             log.debug("   Country:         " + location.getCountry());
             log.debug("   Country Name:    " + unstrip(location.getCountryName()));
             log.debug("   Region:          " + location.getRegion());
             log.debug("   Region Name:     " + unstrip(location.getRegionName()));
             log.debug("   City:            " + location.getCity());
             log.debug("   ISP:             " + location.getISP());
             log.debug("   Latitude:        " + location.getLatitude());
             log.debug("   Longitude:       " + location.getLongitude());
          }
       }
    }

    static void dumpUserLocations(UserLocation[] locations)
    {
       if ((locations == null) || (locations.length == 0))
       {
          log.debug("No user locations.");
       }
       else
       {
          for (int i=0; i<locations.length; i++)
          {
             dumpUserLocation(locations[i]);
          }
          log.debug(locations.length + " user locations returned.");
       }
    }

    static private void dumpPIN(UserPINInfo pin)
    {
       if (pin == null)
       {
          log.debug("No PIN.");
       }
       else
       {
          log.debug("PIN:");
          log.debug("   User:        " + pin.getUserid());
          log.debug("   Name:        " + pin.getUserName());
          log.debug("   Group:       " + pin.getGroup());
          log.debug("   Num Uses:    " + pin.getNumUses());
          log.debug("   Create Date: " + dateFormat(pin.getCreateDate(), ""));
          log.debug("   Expire Date: " + dateFormat(pin.getExpireDate(), "never"));
          String[] pinval = pin.getPIN();
          if (pinval == null)
             log.debug("   PIN:          " + "<hidden>");
          else
          {
             for (int i=0; i<pinval.length; i++)
             {
                if (i == 0)
                {
                   log.debug("   PIN:         " + pinval[i]);
                }
                else
                {
                   log.debug("                " + pinval[i]);
                }
             }
          }
          log.debug("   Comment:      " + pin.getComment());
       }
    }

    static private void dumpOTPs(UserOTPInfo[] otps)
    {
       if ((otps == null) || (otps.length == 0))
       {
          log.debug("No OTPs.");
       }
       else
       {
          for (int i=0; i<otps.length; i++)
          {
             dumpOTP(otps[i]);
          }
          log.debug(otps.length + " OTP(s) returned.");
       }
    }

    static private void dumpOTP(UserOTPInfo otp)
    {
       if (otp == null)
       {
          log.debug("No OTP.");
       }
       else
       {
          log.debug("OTP:");
          log.debug("   User:        " + otp.getUserid());
          log.debug("   Name:        " + otp.getUserName());
          log.debug("   Group:       " + otp.getGroup());
          log.debug("   Create Date: " + dateFormat(otp.getCreateDate(), ""));
          log.debug("   Expire Date: " + dateFormat(otp.getExpireDate(), "never"));
          if (otp.getOTP() == null)
          {
             log.debug("   OTP:          " + "<hidden>");
          }
          else
          {
             log.debug("   OTP:          " + otp.getOTP());
          }
       }
    }
/*    static private void dumpOTP(UserOTPInfo otp)
    {
       if (otp == null)
       {
          log.debug("No OTP.");
       }
       else
       {
          log.debug("OTP:");
          log.debug("   User:        " + otp.getUserid());
          log.debug("   Name:        " + otp.getUserName());
          log.debug("   Group:       " + otp.getGroup());
          log.debug("   Create Date: " +
                             dateFormat(otp.getCreateDate(), ""));
          log.debug("   Expire Date: " +
                             dateFormat(otp.getExpireDate(), "never"));
          if (otp.getOTP() == null)
          {
             log.debug("   OTP:          " + "<hidden>");
          }
          else
          {
             log.debug("   OTP:          " + otp.getOTP());
          }
       }
    }
*/

    static private void dumpPassword(UserPasswordInfo info)
    {
       if (info == null)
       {
          log.debug("No Password.");
          return;
       }
       log.debug("    User:       " + info.getUserid());
       log.debug("    Name:       " + info.getUserName());
       log.debug("   Group:       " + info.getGroup());
       log.debug(" Storage:       " + info.getPasswordStorage().getValue());
       log.debug("Password:       " + info.getRetrievablePassword());
       log.debug("Last Change Date: " + dateFormat(info.getLastChangeDate(), ""));
       log.debug("   Expire Date: " + dateFormat(info.getExpireDate(), "never"));
       log.debug("   ChangeRequired: " + info.getChangeRequired().toString());
       log.debug("   History Size: " + info.getHistorySize().toString());
    }

    static private void dumpIPListEntry(IPListEntry entry)
    {
       if (entry == null)
       {
          log.debug("No IP List Entry.");
       }
       else
       {
          log.debug("IP List Entry:");
          log.debug("   start:  " + entry.getStartIPAddress());
          log.debug("   end:    " + entry.getEndIPAddress());
       }
    }

    static private String dateFormat(Calendar c, String empty)
    {
       if (c == null) return empty;

       if (c.getTime().equals(new Date(0))) return empty;

       SimpleDateFormat dateFormat =
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return dateFormat.format(c.getTime());
    }

    static private String unstrip(String val)
    {
       if (val == null) return "";
       return val;
    }

    static void dumpCards(UserCardInfo[] cards)
    {
       if ((cards == null) || (cards.length == 0))
       {
          log.debug("No cards.");
       }
       else
       {
          for (int i=0; i<cards.length; i++)
          {
             dumpCard(cards[i]);
          }
          log.debug(cards.length + " cards returned.");
       }
    }

    static private void dumpToken(UserTokenInfo token)
    {
       if (token == null)
       {
          log.debug("No user token.");
       }
       else
       {
          log.debug("Token:");
          log.debug("   User:                 " + token.getUserid());
          log.debug("   Name:                 " + token.getUserName());
          log.debug("   Group:                " + token.getGroup());
          log.debug("   Vendor Id:            " + token.getVendorId());
          log.debug("   Serial Number:        " + token.getSerialNumber());
          log.debug("   Token Type:           " + token.getTokenType());
          log.debug("   Response Supported:   " + token.isResponseSupported());
          log.debug("   Challenge Supported:  " + token.isChallengeSupported());
          log.debug("   Unlock Supported:     " + token.isUnlockSupported());
          log.debug("   Signature Supported:  " + token.isSignatureSupported());
          log.debug("   State:                " + token.getState().toString());
          log.debug("   Load Date:            " + dateFormat(token.getLoadDate(), ""));
          log.debug("   Last Used Date:       " + dateFormat(token.getLastUsedDate(), "never"));
          log.debug("   Comment:              " + token.getComment());
       }
    }

    static private void dumpPVN(UserPVNInfo pvn)
    {
       if (pvn == null)
       {
          log.debug("No PVN.");
       }
       else
       {
          log.debug("PVN:");
          log.debug("   User:             " + pvn.getUserid());
          log.debug("   Name:             " + pvn.getUserName());
          log.debug("   Group:            " + pvn.getGroup());
          if (pvn.getPVN() == null)
          {
             log.debug("   PVN:              " + "<hidden>");
          }
          else
          {
             log.debug("   PVN:              " + pvn.getPVN());
          }
          log.debug("   Last Change Date: " + dateFormat(pvn.getLastChangeDate(), ""));
          log.debug("   Change Required:  " + String.valueOf(pvn.isChangeRequired()));
       }
    }

    static private void dumpContactInfoList(ContactInfo[] contactInfoList)
    {
       if ((contactInfoList == null) || (contactInfoList.length == 0))
       {
          log.debug("No ContactInfo.");
       }
       else
       {
          for (int i=0; i<contactInfoList.length; i++)
          {
             dumpContactInfo(contactInfoList[i]);
          }
          log.debug(contactInfoList.length + " contactinfo returned.");
       }
    }

    static void dumpTokens(TokenInfo[] tokens)
    {
       if ((tokens == null) || (tokens.length == 0))
       {
          log.debug("No tokens.");
       }
       else
       {
          for (int i=0; i<tokens.length; i++)
          {
             dumpToken(tokens[i]);
          }
          log.debug(tokens.length + " tokens returned.");
       }
    }

    static private void dumpLockoutInfo(UserInfo user)
    {
       if (user == null)
       {
          log.debug("No lockout info.");
       }
       else
       {
          log.debug("Lockout info:");
          log.debug("   Count:           " + Integer.toString(user.getLockoutInfo().getFailureCount()));
          log.debug("   Expiry Date:     " + dateFormat(user.getLockoutInfo().getLockoutDate(), ""));
       }
    }

    static void dumpSharedSecrets(SharedSecret[] secrets)
    {
       if ((secrets == null) || (secrets.length == 0))
       {
          log.debug("No secrets.");
       }
       else
       {
          for (int i=0; i<secrets.length; i++)
          {
             dumpSharedSecret(secrets[i]);
          }
       }
    }

    static private void dumpSharedSecret(SharedSecret secret)
    {
       if (secret == null)
       {
          log.debug("No secret.");
       }
       else
       {
          log.debug("SharedSecret:");
          log.debug("   Name:            " + secret.getName());
          log.debug("   Value:           " + secret.getValue());
          log.debug("   Write Only:      " +
                             secret.getWriteOnly().toString());
       }
    }

    static private void dumpQASecrets(NameValue[] secrets)
    {
       if ((secrets == null) || (secrets.length == 0))
       {
          log.debug("No QA Secrets.");
       }
       else
       {
          for (int i=0; i<secrets.length; i++)
          {
             dumpQASecret(secrets[i]);
          }
       }
    }

    static private void dumpQASecret(NameValue secret)
    {
       if (secret == null)
       {
          log.debug("No secret.");
       }
       else
       {
          log.debug("QA secret:");
          log.debug("   Question:         " + secret.getName());
          log.debug("   Answer:           " + secret.getValue());
       }
    }

    static void dumpAuthenticationSecrets(NameValue[] secrets)
    {
       if ((secrets == null) || (secrets.length == 0))
       {
          log.debug("No auth secrets.");
       }
       else
       {
          for (int i=0; i<secrets.length; i++)
          {
             dumpAuthenticationSecret(secrets[i]);
          }
       }
    }

    static private void dumpAuthenticationSecret(NameValue secret)
    {
       if (secret == null)
       {
          log.debug("No secret.");
       }
       else
       {
          log.debug("Auth secret:");
          log.debug("   Name:            " + secret.getName());
          log.debug("   Value:           " + secret.getValue());
       }
    }

    static void dumpMachineSecrets(MachineSecretInfo[] secrets)
    {
       if ((secrets == null) || (secrets.length == 0))
       {
          log.debug("No machine secrets.");
       }
       else
       {
          for (int i=0; i<secrets.length; i++)
          {
             dumpMachineSecret(secrets[i]);
          }
       }
    }

    static private void dumpMachineSecret(MachineSecretInfo secret)
    {
       if (secret == null)
       {
          log.debug("No machine secret.");
       }
       else
       {
          log.debug("Machine secret:");
          if (secret.getMachineLabel() == null)
          {
             log.debug("   Machine label:   " + "No label");
          }
          else
          {
             log.debug("   Machine label:   " + secret.getMachineLabel());
          }
          log.debug("   Machine nonce:   " + secret.getMachineNonce());
          log.debug("   Sequence nonce:  " + secret.getSequenceNonce());
          log.debug("   App. data:       ");
          if (secret.getApplicationData() != null)
          {
             for (int i=0; i<secret.getApplicationData().length; i++)
             {
                log.debug("      Name:         " + secret.getApplicationData()[i].getName());
                log.debug("      Value:        " + secret.getApplicationData()[i].getValue());
             }
          }
          log.debug("   Create date:     " + dateFormat(secret.getCreateDate(), "not set"));
          log.debug("   Last used date:  " + dateFormat(secret.getLastUsedDate(), "not set"));
          log.debug("   Expiry date:     " + dateFormat(secret.getExpiryDate(), "not set"));
       }
    }

    static private void dumpContactInfo(ContactInfo contactinfo)
    {
       if (contactinfo == null)
       {
          log.debug("No ContactInfo.");
       }
       else
       {
          log.debug("ContactInfo:");
          // log.debug("   User:            " + contactinfo.getUserid());
          log.debug("   ContactInfo Label:       "
                + contactinfo.getContactInfoLabel());
          log.debug("   Value:                   "
                + contactinfo.getValue());
          log.debug("   Delivery Configuration:  "
                + contactinfo.getDeliveryConfigLabel());
          log.debug("   Default flag:            "
                + contactinfo.isDefaultContactInfo());
       }
    }

    static private String expectedLocationsToString(ExpectedLocation[] el)
    {
       if (el == null) return "";
       String res = "";
       for (int i=0; i<el.length; i++)
       {
          if (i > 0) res += " ";
          res += expectedLocationToString(el[i]);
       }

       return res;
    }

    static private String expectedLocationToString(ExpectedLocation el)
    {
       if (el == null) return "";

       if (el.isPrivateAddress())
       {
          String res = "private";
          if (el.getIPAddress() == null) return res;
          res += "/";
          res += el.getIPAddress();
          return res;
       }
       else
       {
          String res = "";
          if (el.getIPAddress() != null)
          {
             res = unstrip(el.getCountry()) + "/" +
                   unstrip(el.getRegion()) + "/" +
                   unstrip(el.getCity()) + "/" +
                   unstrip(el.getISP()) + "/" +
                   unstrip(el.getIPAddress());
          }
          else if (el.getISP() != null)
          {
             res = unstrip(el.getCountry()) + "/" +
                   unstrip(el.getRegion()) + "/" +
                   unstrip(el.getCity()) + "/" +
                   unstrip(el.getISP());
          }
          else if (el.getCity() != null)
          {
             res = unstrip(el.getCountry()) + "/" +
                   unstrip(el.getRegion()) + "/" +
                   unstrip(el.getCity());
          }
          else if (el.getRegion() != null)
          {
             res = unstrip(el.getCountry()) + "/" +
                   unstrip(el.getRegion());
          }
          else
          {
             res = unstrip(el.getCountry());
          }

          return res;
       }
    }

    static private void dumpCard(UserCardInfo card)
    {
       if (card == null)
       {
          log.debug("No Card.");
       }
       else
       {
          log.debug("Card:");
          log.debug("   User:            " + card.getUserid());
          log.debug("   Name:            " + card.getUserName());
          log.debug("   Group:           " + card.getGroup());
          log.debug("   Serial:          " + card.getSerialNumber());
          log.debug("   State:           " +
                             card.getState().toString());
          log.debug("   Create Date:     " +
                             dateFormat(card.getCreateDate(), ""));
          log.debug("   Expire Date:     " +
                             dateFormat(card.getExpireDate(), "never"));
          log.debug("   Superseded Date: " +
                             dateFormat(card.getExpireDate(), "N/A"));
          log.debug("   Comment:         " + card.getComment());
          log.debug("   Preproduced:     " + card.getPreproduced());
          String[][] cardGrid = convertGridToArray(card.getGrid());
          String message =   "<hidden>";
          if(cardGrid != null)
             message = dumpGrid(cardGrid);
          log.debug("   Grid:            " + message);
          Integer challengeCount = card.getChallengeCount();
          if (challengeCount != null)
          {
             log.debug("   ChallengeCount:              "
                   + challengeCount.intValue());
          }
          Integer leastUsedCount = card.getLeastUsedCellUsageCount();
          if (leastUsedCount != null)
          {
             log.debug("   LeastUsedCellUsageCount:     "
                   + leastUsedCount.intValue());
          }
          CardUsageThresholdIndicator indicator =
             card.getCardUsageThresholdIndicator();
          if (indicator != null)
          {
             log.debug("   CardUsageThresholdIndicator: "
                   + indicator.getValue());
          }
       }
    }

    static private String dumpGrid(String[][] g)
    {
       String space = "	";
       String seperator = "--";
       String newLine = "\n";
       String tab = " ";
       StringBuffer grid_string = new StringBuffer(newLine + space + "    ");
       for (int i = 0; i < g.length; i++)
       {
          String[] cells = g[i];
          if (i == 0)
          {
             char ch = 'A';
             for (int ii = 0; ii < cells.length; ii++)
             {
                grid_string.append(ch + tab);
                int ascii = (int) ch;
                ascii++;
                ch = (char)ascii;
             }
             grid_string.append(newLine + space + "   ");
             for (int ij = 0; ij < cells.length; ij++)
             {
                grid_string.append(seperator);
             }
             grid_string.append(newLine + space);
          }
          grid_string.append(i + 1);
          grid_string.append(" |" + tab);
          for (int j = 0; j < cells.length; j++)
          {
             grid_string.append(cells[j] + tab);
          }
          grid_string.append(newLine + space);
       }
       return grid_string.toString();
    }

    static private String[][] convertGridToArray(Grid grid)
    {
       String[][] retVal = null;
       if (grid != null)
       {
          retVal = new String[grid.getNumRows()][grid.getNumColumns()];
          GridCell[] cells = grid.getCells();
          for (int i = 0; i < cells.length; i++)
          {
             retVal[cells[i].getRow()][cells[i].getColumn()]
                                          = cells[i].getValue();
          }
       }
       return retVal;
    }

    static void dumpTokens(UserTokenInfo[] tokens)
    {
       if ((tokens == null) || (tokens.length == 0))
       {
          log.debug("No user tokens.");
       }
       else
       {
          for (int i=0; i<tokens.length; i++)
          {
             dumpToken(tokens[i]);
          }
          log.debug(tokens.length + " tokens returned.");
       }
    }

    static private void dumpToken(TokenInfo token)
    {
       if (token == null)
       {
          log.debug("No token.");
       }
       else
       {
          log.debug("Token:");
          log.debug("   Vendor Id:            " + token.getVendorId());
          log.debug("   Serial Number:        " + token.getSerialNumber());
          log.debug("   Group:                " + token.getGroup());
          log.debug("   Token Type:           " + token.getTokenType());
          log.debug("   Response Supported:   " + token.isResponseSupported());
          log.debug("   Challenge Supported:  " + token.isChallengeSupported());
          log.debug("   Unlock Supported:     " + token.isUnlockSupported());
          log.debug("   Signature Supported:  " + token.isSignatureSupported());
          log.debug("   Load Date:            " + dateFormat(token.getLoadDate(), ""));
          log.debug("   Comment:              " + token.getComment());
       }
    }

}
