package com.example.interoperability

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@Validated
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getUserByID(@PathVariable id: Long): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@Valid @RequestBody user: User): User {
        return userRepository.save(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun updateUserEmail(@PathVariable id: Long, @RequestBody emailUpdate: EmailUpdate): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            val updateUser = user.get().apply { email = emailUpdate.email }
            ResponseEntity.ok(userRepository.save(updateUser))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

data class EmailUpdate(val email: String)