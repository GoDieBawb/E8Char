/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserver.posts;

import com.google.gson.Gson;
import java.time.LocalDate;

import webserver.authentication.Authenticator;
import webserver.responses.*;

/**
 *
 * @author MeanC
 */
public class PostHandler {

    private final Authenticator authenticator;

    public PostHandler() {
        authenticator = new Authenticator();
    }

    public BasicResponse handle(String json) {

        Gson      g   = new Gson();
        BasicPost b   = g.fromJson(json, BasicPost.class);

        //Handle Login
        if (b.postType.equals("Login")) {
            LoginPost l = g.fromJson(json, LoginPost.class);
            //System.out.println("Checking Login: " + l.username + " " + l.password);
            String token    = authenticator.Authenticate(l.username, l.password);
            LoginResponse r = new LoginResponse(l.username);

            //If Token is Null Login Failed
            if (token == null) {
                r.outcome       = "failed";
                r.accessToken   = "none";
                return r;
            }

            r.accessToken   = token;
            return r;
        }

        //Verify Token
        else {
            //System.out.println("Checking Token: " + b.accessToken);
            String user = authenticator.Authenticate(b.accessToken);

            //If user is null then the Access Token is Bad
            if (user == null) {
                System.out.println("BAD OR EXPIRED TOKEN");
                return new BadTokenResponse();
            }
            // System.out.println("Verified!");
        }

        // Check if clinician has valid access to the patient for the proper request.
        if (b.patientId != -1)
            if (!authenticator.Authenticate(b.accessToken, b.patientId))
                return new BadTokenResponse();

        //Once Token Verified Determine Which Post Type
        switch (b.postType) {

            case "appointmentFormPost":
                AppointmentFormPost afp = g.fromJson(json, AppointmentFormPost.class);
                afp.enteredby = authenticator.getUserIdByToken(afp.accessToken);
                afp.entereddate = LocalDate.now().toString();
                afp.publish();
                return new BasicResponse("success");

            case "bloodDrawnFormPost":
                SubmitBloodDrawPost sbp = g.fromJson(json, SubmitBloodDrawPost.class);
                sbp.enteredby   = authenticator.getUserIdByToken(sbp.accessToken);
                sbp.entereddate = LocalDate.now().toString();
                sbp.publish();
                return new BasicResponse("success");

            case "procedureConsentFormPost":
                SubmitProcedureConsentFormPost cfp = g.fromJson(json, SubmitProcedureConsentFormPost.class);
                cfp.enteredby   = authenticator.getUserIdByToken(cfp.accessToken);
                cfp.entereddate = LocalDate.now().toString();
                cfp.publish();
                return new BasicResponse("success");

            case "immunizationRecordFormPost":
                SubmitImmunizationRecordPost sip = g.fromJson(json, SubmitImmunizationRecordPost.class);
                sip.enteredby   = authenticator.getUserIdByToken(sip.accessToken);
                sip.entereddate = LocalDate.now().toString();
                sip.publish();
                return new BasicResponse("success");

            case "medicationRecordFormPost":
                SubmitPrescriptionForm smp = g.fromJson(json, SubmitPrescriptionForm.class);
                smp.enteredby   = authenticator.getUserIdByToken(smp.accessToken);
                smp.entereddate = LocalDate.now().toString();
                smp.publish();
                return new BasicResponse("success");

            case "surgicalHistoryFormPost":
                SurgicalHistoryPost shp = g.fromJson(json, SurgicalHistoryPost.class);
                shp.enteredby   = authenticator.getUserIdByToken(shp.accessToken);
                shp.entereddate = LocalDate.now().toString();
                shp.publish();
                return new BasicResponse("success");

            case "patientRegistrationFormPost":
                SubmitPatientPost sdp = g.fromJson(json, SubmitPatientPost.class);
                sdp.enteredBy   = authenticator.getUserIdByToken(sdp.accessToken);
                sdp.enteredDate = LocalDate.now().toString();
                sdp.publish();
                return new BasicResponse("success");

            case "mentalHealthEvaluationFormPost":
                SubmitMHEvalPost smhe = g.fromJson(json, SubmitMHEvalPost.class);
                smhe.enteredby   = authenticator.getUserIdByToken(smhe.accessToken);
                smhe.entereddate = LocalDate.now().toString();
                smhe.publish();
                return new BasicResponse("success");

            case "physicalEvaluationFormPost":
                SubmitPhysicalPost smpe = g.fromJson(json, SubmitPhysicalPost.class);
                smpe.enteredby   = authenticator.getUserIdByToken(smpe.accessToken);
                smpe.entereddate = LocalDate.now().toString();
                smpe.publish();
                return new BasicResponse("success");

            case "requestUserClients":
                int userId = authenticator.getUserIdByToken(b.accessToken);
                UserClientsResponse clientsResponse = new UserClientsResponse(userId);
                return clientsResponse;

            case "requestClientServices":
                int requestingUser         = authenticator.getUserIdByToken(b.accessToken);
                ClientServicesResponse csr = new ClientServicesResponse(requestingUser, b.patientId);
                return csr;

            case "requestClientDemographic":
                requestingUser = authenticator.getUserIdByToken(b.accessToken);
                ClientDemographicResponse cdr = new ClientDemographicResponse(requestingUser, b.patientId);
                return cdr;
                
            default:
                System.out.println("Unknown Post: " + b.postType);
                return new UnknownPostResponse();
        }

    }

}
