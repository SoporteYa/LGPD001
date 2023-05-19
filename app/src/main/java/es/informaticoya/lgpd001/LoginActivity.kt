package es.informaticoya.lgpd001

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.informaticoya.lgpd001.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.btnSignIn.setOnClickListener {
            if (binding.etEmail.text.isEmpty() && binding.etPassword.text.isEmpty()) {
                Toast.makeText(this, "Rellenar datos", Toast.LENGTH_SHORT).show()
            } else {
                authentication()
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }

        binding.btnLoginIn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }



    private fun authentication() {
        auth.createUserWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString())
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                }else {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                }
            }
        }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
      }
    }
