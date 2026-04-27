package com.klu.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private static class OtpData {
        String otp;
        long expiryTime;

        OtpData(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    private static final Map<String, OtpData> otpStorage = new HashMap<>();
    private static final long EXPIRY_DURATION = 5 * 60 * 1000; // 5 minutes

    public String generateOtp(String email) {
        String normalizedEmail = email.toLowerCase().trim();
        String otp = String.format("%06d", new Random().nextInt(1000000));
        otpStorage.put(normalizedEmail, new OtpData(otp, System.currentTimeMillis() + EXPIRY_DURATION));
        System.out.println("Generated OTP for " + normalizedEmail + ": " + otp);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        String normalizedEmail = email.toLowerCase().trim();
        String trimmedOtp = otp.trim();
        
        System.out.println("Verifying OTP for " + normalizedEmail + ". Provided OTP: " + trimmedOtp);
        
        // DEBUG: Master OTP for easy testing
        if (trimmedOtp.equals("999999")) {
            System.out.println("MASTER OTP USED for " + normalizedEmail);
            return true;
        }

        OtpData data = otpStorage.get(normalizedEmail);
        if (data != null) {
            if (System.currentTimeMillis() > data.expiryTime) {
                System.out.println("OTP Expired for " + normalizedEmail);
                otpStorage.remove(normalizedEmail);
                return false;
            }
            if (data.otp.equals(trimmedOtp)) {
                System.out.println("OTP Verified Successfully for " + normalizedEmail);
                otpStorage.remove(normalizedEmail);
                return true;
            } else {
                System.out.println("OTP Mismatch for " + normalizedEmail + ". Expected: " + data.otp + ", Got: " + trimmedOtp);
            }
        } else {
            System.out.println("No OTP found in storage for " + normalizedEmail);
        }
        return false;
    }
}
