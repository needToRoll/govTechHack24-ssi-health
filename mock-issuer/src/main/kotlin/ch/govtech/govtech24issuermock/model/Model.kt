package ch.govtech.govtech24issuermock.model

import com.fasterxml.jackson.annotation.*
import java.time.ZonedDateTime


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = Medication::class, name = "Medication"),
    JsonSubTypes.Type(value = AllergyIntolerance::class, name = "AllergyIntolerance"),
    JsonSubTypes.Type(value = Diagnosis::class, name = "Diagnosis")
)
abstract class Credential {
    abstract var id: String
    abstract var type: String
    abstract var issueDate: ZonedDateTime
}

data class Medication @JsonCreator constructor(
    var content: MedicationContent,
    override var id: String = "",
    @JsonProperty("type") override var type: String = "Medication",
    override var issueDate: ZonedDateTime = ZonedDateTime.now()
) : Credential()

data class MedicationContent(
    var medicationCode: String,
    var medicationName: String,
    var dosageInstruction: String
)

data class AllergyIntolerance @JsonCreator constructor(
    override var id: String,
    override var type: String = "AllergyIntolerance",
    override var issueDate: ZonedDateTime = ZonedDateTime.now(),
    var content: AllergyIntoleranceContent
) : Credential()

data class Diagnosis @JsonCreator constructor(
    override var id: String,
    override var type: String = "Diagnosis",
    override var issueDate: ZonedDateTime = ZonedDateTime.now(),
    var content: DiagnosisContent
) : Credential()

data class DiagnosisContent @JsonCreator constructor(
    val diagnosisCode: String,
    val diagnosisDescription: String
)

data class AllergyIntoleranceContent(
    var allergyCode: String,
    var allergyDescription: String,
    var reactionCode: String,
    var severity: Int
)