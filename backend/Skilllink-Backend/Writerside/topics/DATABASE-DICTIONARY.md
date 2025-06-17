# DATABASE-DICTIONARY


## 📖 DATABASE DICTIONARY – SkillLink: E-Learning Module

> ✅ Format:

- **Table Name**

- Column Name

- Data Type

- Nullable

- Description


---

### 🧑‍🎓 `usuario`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key, unique ID of user|
|nombre|VARCHAR(100)|NO|User's first name|
|apellido|VARCHAR(100)|NO|User's last name|
|correo|VARCHAR(150)|NO|User's email, unique|
|contraseña|TEXT|NO|Encrypted password|
|foto|TEXT|YES|Profile picture URL|
|biografia|TEXT|YES|User biography|
|rol|VARCHAR(50)|NO|Role: ADMIN, MENTOR, LEARNER|

---

### 🧾 `perfil`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|usuario_id|BIGINT|NO|Foreign key to `usuario`|
|experiencia|TEXT|YES|User's experience summary|
|educacion|TEXT|YES|Education background|
|certificaciones|TEXT|YES|Text summary or IDs of certifications|

---

### 🎓 `curso`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|titulo|VARCHAR(200)|NO|Title of the course|
|descripcion|TEXT|YES|Course description|
|instructor_id|BIGINT|NO|Foreign key to `usuario`|
|duracion|INT|YES|Duration in hours|
|certificacion_id|BIGINT|YES|FK to `certificacion`|
|nivel|VARCHAR(50)|NO|Course level: BASICO, INTERMEDIO, AVANZADO|

---

### 📜 `certificacion`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|nombre|VARCHAR(200)|NO|Name of the certificate|
|institucion|VARCHAR(150)|YES|Issuing institution|
|fecha_emision|DATE|NO|Date of issue|
|expiracion|DATE|YES|Expiry date if any|

---

### 🤝 `mentoria`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|mentor_id|BIGINT|NO|FK to `usuario`|
|aprendiz_id|BIGINT|NO|FK to `usuario`|
|fecha_inicio|DATE|NO|Start date|
|fecha_fin|DATE|YES|End date|
|estado|VARCHAR(50)|NO|PENDIENTE, ACEPTADA, TERMINADA|

---

### ⚡ `habilidad`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|nombre|VARCHAR(100)|NO|Skill name|
|descripcion|TEXT|YES|Skill description|

---

### 🧠 `usuario_habilidad` (Join Table)

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|usuario_id|BIGINT|NO|FK to `usuario`|
|habilidad_id|BIGINT|NO|FK to `habilidad`|

---

### 🧪 `desafio`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|titulo|VARCHAR(200)|NO|Challenge title|
|descripcion|TEXT|YES|Challenge details|
|creador_id|BIGINT|NO|FK to `usuario`|
|estado|VARCHAR(50)|NO|ACTIVO, CERRADO|
|resultados|TEXT|YES|Results or summary (JSON string)|

---

### ✉️ `mensaje`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|remitente_id|BIGINT|NO|Sender (FK to `usuario`)|
|receptor_id|BIGINT|NO|Receiver (FK to `usuario`)|
|contenido|TEXT|NO|Message body|
|fecha_envio|TIMESTAMP|NO|Date & time sent|
|estado|BOOLEAN|NO|`true` if read, `false` if unread|

---

### 🔔 `notificacion`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|usuario_id|BIGINT|NO|FK to `usuario`|
|tipo|VARCHAR(100)|NO|Notification type|
|contenido|TEXT|NO|Notification body|
|fecha_creacion|TIMESTAMP|NO|Time created|
|estado|BOOLEAN|NO|`true` if seen|

---

### 📝 `evaluacion`

|Column Name|Type|Nullable|Description|
|---|---|---|---|
|id|BIGINT|NO|Primary key|
|evaluador_id|BIGINT|NO|Mentor (FK to `usuario`)|
|evaluado_id|BIGINT|NO|Learner (FK to `usuario`)|
|puntaje|INT|NO|Score (1–5)|
|comentarios|TEXT|YES|Feedback|
|fecha_creacion|TIMESTAMP|NO|Time of evaluation|

---

## 🧠 Optional Enhancements (Pro Tips)

- Add `@CreatedDate` / `@LastModifiedDate` using `@EntityListeners(AuditingEntityListener.class)`

- Use `UUID` instead of `BIGINT` if you want better security for public IDs

- Normalize enums using `@Enumerated(EnumType.STRING)` in JPA

- Create **indexes** on foreign keys and `correo`, `estado`, `usuario_id`


---

