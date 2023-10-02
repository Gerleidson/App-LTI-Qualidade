package com.example.ltiqualidade;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
public class instalacao extends AppCompatActivity {
    private EditText nomeEditText;
    private EditText enderecoEditText;
    private EditText bairroEditText;
    private EditText cidadeEditText;
    private TextView cpfTextView;
    private TextView urgenteEditText;
    private TextView contatoTextView;
    private TextView anotacaoTextView;
    private Spinner formaPagamentoSpinner;
    private Spinner cpfSpinner;
    private EditText contatoEditText;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //tema com fundo branco
        setTheme(R.style.theme_main);

        //tema da tela splash
        setContentView(R.layout.activity_manutencao);

        // Spinner element cpf/cnpj
        Spinner spinner1 = findViewById(R.id.spinner1);

        //defenindo limite de caractere para item selecionado no spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();

                if (selectedItem.equals("CPF: ")) {
                    cpfTextView.setFilters(new InputFilter[] { new InputFilter.LengthFilter(11) });
                } else if (selectedItem.equals("CNPJ: ")) {
                    cpfTextView.setFilters(new InputFilter[] { new InputFilter.LengthFilter(14) });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Implemente conforme necessário
            }
        });

        // Spinner Drop down elements
        List<String> categories1 = new ArrayList<String>();
        categories1.add("CPF: ");
        categories1.add("CNPJ: ");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        // Spinner element forma de pagamento
        Spinner spinner = findViewById(R.id.spinner2);

        // Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("Dinheiro");
        categories2.add("PIX");
        categories2.add("Cartão de crédito");
        categories2.add("Cartão de débito");

        // Creating adapter for spinner
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        cpfSpinner = findViewById(R.id.spinner1);
        formaPagamentoSpinner = findViewById(R.id.spinner2);
        nomeEditText = findViewById(R.id.nomeEditText);
        enderecoEditText = findViewById(R.id.enderecoEditText);
        bairroEditText = findViewById(R.id.bairroEditText);
        cidadeEditText = findViewById(R.id.cidadeEditText);
        cpfTextView = findViewById(R.id.cpf);
        urgenteEditText = findViewById(R.id.urgenteEditText);
        contatoTextView = findViewById(R.id.contato);
        anotacaoTextView = findViewById(R.id.anotacao);

        // Configuração do botão de compartilhamento
        FloatingActionButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compartilharResultados();
            }
        });

        // Impede que o teclado abra automaticamente ao iniciar a atividade
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Configura o ajuste automático da janela para o teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        //limita  o campo contato para 11 caractere
        contatoEditText = findViewById(R.id.contato);
        contatoEditText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(11) });
    }

    // Compartilha os resultados
    @SuppressLint("QueryPermissionsNeeded")
    private void compartilharResultados() {
        String nome = nomeEditText.getText().toString();
        String endereco = enderecoEditText.getText().toString();
        String bairro = bairroEditText.getText().toString();
        String cidade = cidadeEditText.getText().toString();
        String cpf2 = cpfTextView.getText().toString();
        String urgente = urgenteEditText.getText().toString();
        String anotacao = anotacaoTextView.getText().toString();
        String formaPagamento = formaPagamentoSpinner.getSelectedItem().toString();
        String cpf = cpfSpinner.getSelectedItem().toString();

        // Obtém o valor do contato

        String contato = PhoneNumberUtils.formatNumber(contatoTextView.getText().toString(), "BR");

        // Remove todos os caracteres que não sejam dígitos
        String numeroFormatado = PhoneNumberUtils.stripSeparators(contato);

        // Formata o número no padrão "(xx) xxxxx-xxxx"
        String numeroFormatadoExibicao = PhoneNumberUtils.formatNumber(numeroFormatado, "BR");

        // Verifica se o número foi formatado corretamente
        if (numeroFormatadoExibicao == null) {
            Toast.makeText(this, "Contato inválido. Insira um número de telefone válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualiza o valor da variável contato com o número formatado
        contato = numeroFormatadoExibicao;

        String mensagem = "Agendamento para instalação:" +
                "\nNome: " + nome +
                "\nEndereço: " + endereco +
                "\nBairro: " + bairro +
                "\nCidade: " + cidade +
                "\n" + cpf + cpf2 +
                "\nUrgente: " + urgente +
                "\nContato: " + contato +
                "\nAnotação: " + anotacao +
                "\nForma de Pagamento: " + formaPagamento;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        Intent chooser = Intent.createChooser(intent, "Compartilhar via");
        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Toast.makeText(this, "Nenhum aplicativo de compartilhamento disponível.", Toast.LENGTH_SHORT).show();
        }
    }
}