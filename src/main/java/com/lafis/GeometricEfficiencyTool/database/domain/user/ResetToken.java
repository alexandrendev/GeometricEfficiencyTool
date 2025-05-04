package com.lafis.GeometricEfficiencyTool.database.domain.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "resetToken")
@Data
@Builder
public class ResetToken {

    @Id
    String id;

    String userId;

    Instant createdAt;

    Instant expiresAt;

    Boolean isUsed;
}