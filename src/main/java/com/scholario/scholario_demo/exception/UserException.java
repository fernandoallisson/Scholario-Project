package com.scholario.scholario_demo.exception;

public class UserException extends RuntimeException {

  // Construtor específico para erros Genéricos
  public UserException(String message) {
    super(message);
  }
  // Construtor específico para erro de "Not Found"
  public static UserException notFound(String resourceName, Object resourceId) {
    return new UserException(resourceName + " with ID " + resourceId + " not found");
  }

  // Construtor específico para erro de "Unauthorized"
  public static UserException unauthorized() {
    return new UserException("Unauthorized access.");
  }

  // Construtor específico para erro de "Bad Request"
  public static UserException badRequest(String details) {
    return new UserException("Bad request: " + details);
  }

  // Outros tipos de erro que você pode querer adicionar:
  public static UserException conflict(String resourceName) {
    return new UserException(resourceName + " already exists.");
  }

  public static UserException internalServerError() {
    return new UserException("An internal server error occurred.");
  }
}
