# EntityDiagram-conceptual

Perfect, I’ve extracted the **e-learning (Educación en Línea)** section from your PDF and built the following for you:

---

## 🎓 MER – E-Learning (Educación en Línea)

### 🔷 Objective

Model relationships between users, courses, certifications, mentorships, and evaluations within an interactive learning platform.

---

## 🧱 ENTITY DIAGRAM (Conceptual Design)

Here’s a simplified Entity-Relationship Model (MER) based on what you gave:

### 🧩 ENTITIES

|Entity|Attributes|
|---|---|
|**Usuario**|`id`, `nombre`, `apellido`, `correo`, `contraseña`, `foto`, `biografía`, `rol`|
|**Perfil**|`id`, `usuario_id`, `experiencia`, `educacion`, `certificaciones`|
|**Curso**|`id`, `titulo`, `descripcion`, `instructor_id`, `duracion`, `certificacion_id`, `nivel`|
|**Certificación**|`id`, `nombre`, `institucion`, `fecha_emision`, `expiracion`|
|**Mentoría**|`id`, `mentor_id`, `aprendiz_id`, `fecha_inicio`, `fecha_fin`, `estado`|
|**Habilidad**|`id`, `nombre`, `descripcion`|
|**Desafío**|`id`, `titulo`, `descripcion`, `creador_id`, `estado`, `resultados`|
|**Mensaje**|`id`, `remitente_id`, `receptor_id`, `contenido`, `fecha_envio`, `estado`|
|**Notificación**|`id`, `usuario_id`, `tipo`, `contenido`, `fecha_creacion`, `estado`|
|**Evaluación**|`id`, `evaluador_id`, `evaluado_id`, `puntaje`, `comentarios`, `fecha_creacion`|

---

## 🔗 RELATIONSHIPS

|Relationship|Type|Notes|
|---|---|---|
|Usuario ↔ Perfil|1:1|One profile per user|
|Usuario ↔ Curso|N:M|A user can enroll in multiple courses|
|Curso ↔ Certificación|1:1|One certification per course|
|Usuario ↔ Mentoría|N:M|A user can be mentor/apprentice in many mentorships|
|Mentor ↔ Evaluación|1:N|A mentor can evaluate many learners|
|Usuario ↔ Habilidad|N:M|A user has many skills|
|Usuario ↔ Mensaje|1:N|A user can send/receive many messages|
|Usuario ↔ Notificación|1:N|A user can receive many notifications|
|Desafío ↔ Evaluación|1:N|One challenge can generate multiple evaluations|

---

## 📘 ENTITY DOCUMENTATION (UML-style Summary)

### 📍 `Usuario`

```java
Usuario {
  Long id;
  String nombre;
  String apellido;
  String correo;
  String contraseña;
  String foto;
  String biografía;
  Enum rol; // ADMIN, MENTOR, LEARNER
}
```

### 📍 `Perfil`

```java
Perfil {
  Long id;
  Usuario usuario;
  String experiencia;
  String educacion;
  String certificaciones;
}
```

### 📍 `Curso`

```java
Curso {
  Long id;
  String titulo;
  String descripcion;
  Usuario instructor;
  Integer duracion; // in hours
  Certificación certificacion;
  Enum nivel; // BASICO, INTERMEDIO, AVANZADO
}
```

### 📍 `Certificación`

```java
Certificacion {
  Long id;
  String nombre;
  String institucion;
  LocalDate fechaEmision;
  LocalDate expiracion;
}
```

### 📍 `Mentoría`

```java
Mentoria {
  Long id;
  Usuario mentor;
  Usuario aprendiz;
  LocalDate fechaInicio;
  LocalDate fechaFin;
  Enum estado; // PENDIENTE, ACEPTADA, TERMINADA
}
```

### 📍 `Habilidad`

```java
Habilidad {
  Long id;
  String nombre;
  String descripcion;
}
```

### 📍 `Desafío`

```java
Desafio {
  Long id;
  String titulo;
  String descripcion;
  Usuario creador;
  Enum estado; // ACTIVO, CERRADO
  String resultados; // JSON summary or link to results
}
```

### 📍 `Mensaje`

```java
Mensaje {
  Long id;
  Usuario remitente;
  Usuario receptor;
  String contenido;
  LocalDateTime fechaEnvio;
  Boolean estadoLeido;
}
```

### 📍 `Notificación`

```java
Notificacion {
  Long id;
  Usuario usuario;
  String tipo; // EJ: MENTORÍA, MENSAJE, NUEVO CURSO
  String contenido;
  LocalDateTime fechaCreacion;
  Boolean estadoLeido;
}
```

### 📍 `Evaluación`

```java
Evaluacion {
  Long id;
  Usuario evaluador;
  Usuario evaluado;
  Integer puntaje;
  String comentarios;
  LocalDateTime fechaCreacion;
}
```

---

## 🎯 Next Step?

If you want, I can:

- Generate the **SQL Schema** or **JPA entity classes** (with annotations)

- Visualize this as a **diagram (ERD)** or in Notion/Markdown

- Build **DTOs + Repositories + Services** for this module
    

