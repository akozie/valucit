package com.twoam.valucit.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.twoam.valucit.R
import com.twoam.valucit.adapter.AllCurrencyAdapter
import com.twoam.valucit.adapter.CountryAdapter
import com.twoam.valucit.adapter.GenericRecyclerBindingInterface
import com.twoam.valucit.databinding.FragmentRegisterBinding
import com.twoam.valucit.model.dashboard.AllCurrency
import com.twoam.valucit.model.dashboard.Country
import com.twoam.valucit.model.dashboard.CountryModel
import java.util.*


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    var hour = 0
    var minutes = 0
    var seconds = 0

    var savedHour = 0
    var savedMinutes = 0

    private lateinit var dobListener: DatePickerDialog.OnDateSetListener
    private lateinit var date: Calendar
    private lateinit var progressBar: ProgressBar
    private val totalQuestions = 7 // Adjust this number based on the total number of questions
    private var answeredQuestions = 0
    private var wasEmpty = true
    private lateinit var countryList: ArrayList<CountryModel>
    private lateinit var country: String
    private lateinit var currency: String
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var allCurrencyAdapter: AllCurrencyAdapter
    private lateinit var countryView: AutoCompleteTextView
    private lateinit var currencyView: AutoCompleteTextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryView = binding.country
        currencyView = binding.currency
        // Initially hide other questions
        binding.constraintLayout4.visibility = View.GONE
        binding.constraintLayout5.visibility = View.GONE
        binding.constraintLayout6.visibility = View.GONE
        binding.constraintLayout7.visibility = View.GONE
        binding.constraintLayout8.visibility = View.GONE
        binding.constraintLayout9.visibility = View.GONE

        progressBar = binding.progressBar
        progressBar.max = totalQuestions

        val scroll = binding.scrollView
        binding.firstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout4.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 1
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 0
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout4.visibility = View.GONE
                        binding.constraintLayout5.visibility = View.GONE
                        binding.constraintLayout6.visibility = View.GONE
                        binding.constraintLayout7.visibility = View.GONE
                        binding.constraintLayout8.visibility = View.GONE
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }

        })
        binding.familyName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout5.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 2
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 1
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout5.visibility = View.GONE
                        binding.constraintLayout6.visibility = View.GONE
                        binding.constraintLayout7.visibility = View.GONE
                        binding.constraintLayout8.visibility = View.GONE
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }
        })
        binding.dateOfBirth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout6.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 3
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 2
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout6.visibility = View.GONE
                        binding.constraintLayout7.visibility = View.GONE
                        binding.constraintLayout8.visibility = View.GONE
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }

        })

        // Create a list of countries
        val listOfCountries =  listOf(
            Country("🇳🇬", "Nigeria"),
            Country("🇲🇦", "Morocco"),
            Country("🇱🇾", "Libya"),
            Country("🇺🇸", "United States"),
            Country("🇨🇦", "Canada"),
            Country("🇬🇧", "United Kingdom"),
            Country("🇯🇵", "Japan"),
            Country("🇮🇳", "India"),
            Country("🇦🇺", "Australia"),
            Country("🇫🇷", "France"),
            Country("🇧🇷", "Brazil"),
            Country("🇩🇪", "Germany"),
            Country("🇿🇦", "South Africa"),
            Country("🇮🇹", "Italy"),
            Country("🇪🇸", "Spain"),
            Country("🇨🇳", "China"),
            Country("🇲🇽", "Mexico"),
            Country("🇷🇺", "Russia"),
            Country("🇰🇷", "South Korea"),
            Country("🇸🇦", "Saudi Arabia"),
            Country("🇦🇷", "Argentina"),
            Country("🇹🇷", "Turkey"),
            Country("🇳🇱", "Netherlands"),
            Country("🇸🇪", "Sweden"),
            Country("🇨🇭", "Switzerland"),
            Country("🇧🇪", "Belgium"),
            Country("🇦🇹", "Austria"),
            Country("🇳🇴", "Norway"),
            Country("🇩🇰", "Denmark"),
            Country("🇵🇹", "Portugal"),
            Country("🇬🇷", "Greece"),
            Country("🇵🇱", "Poland"),
            Country("🇮🇪", "Ireland"),
            Country("🇫🇮", "Finland"),
            Country("🇨🇴", "Colombia"),
            Country("🇨🇱", "Chile"),
            Country("🇵🇪", "Peru"),
            Country("🇻🇪", "Venezuela"),
            Country("🇳🇿", "New Zealand"),
            Country("🇮🇱", "Israel"),
            Country("🇵🇭", "Philippines"),
            Country("🇮🇩", "Indonesia"),
            Country("🇲🇾", "Malaysia"),
            Country("🇸🇬", "Singapore"),
            Country("🇹🇭", "Thailand"),
            Country("🇻🇳", "Vietnam"),
            Country("🇦🇪", "United Arab Emirates"),
            Country("🇶🇦", "Qatar"),
            Country("🇰🇼", "Kuwait"),
            Country("🇪🇬", "Egypt"),
            Country("🇰🇪", "Kenya"),
            Country("🇬🇭", "Ghana"),
            Country("🇿🇲", "Zambia"),
            Country("🇿🇼", "Zimbabwe"),
            Country("🇺🇬", "Uganda"),
            Country("🇹🇿", "Tanzania"),
            Country("🇪🇹", "Ethiopia"),
            Country("🇸🇳", "Senegal"),
            Country("🇿🇦", "South Africa"),
            Country("🇰🇪", "Kenya"),
            Country("🇩🇿", "Algeria"),
            Country("🇹🇳", "Tunisia"),
            Country("🇩🇿", "Algeria"),
            Country("🇹🇳", "Tunisia")
        )

        // Create an instance of the custom adapter
         countryAdapter = CountryAdapter(requireContext(), listOfCountries)
        countryView.setAdapter(countryAdapter)

        countryView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val countryList =
                    adapterView?.getItemAtPosition(p2) as Country
                country = countryList.name
                countryView.setText(country)
            }
        }

        countryView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout7.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 4
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 3
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout7.visibility = View.GONE
                        binding.constraintLayout8.visibility = View.GONE
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }

        })

        // Create a list of currencies
        val allCurrencyList = listOf(
            AllCurrency("CAD", "Canadian Dollar", "CAD - Canadian Dollar"),
            AllCurrency("USD", "United States Dollar", "USD - United States Dollar"),
            AllCurrency("EUR", "Euro", "EUR - Euro"),
            AllCurrency("GBP", "British Pound Sterling", "GBP - British Pound Sterling"),
            AllCurrency("JPY", "Japanese Yen", "JPY - Japanese Yen"),
            AllCurrency("AUD", "Australian Dollar", "AUD - Australian Dollar"),
            AllCurrency("CHF", "Swiss Franc", "CHF - Swiss Franc"),
            AllCurrency("CNY", "Chinese Yuan", "CNY - Chinese Yuan"),
            AllCurrency("INR", "Indian Rupee", "INR - Indian Rupee"),
            AllCurrency("RUB", "Russian Ruble", "RUB - Russian Ruble"),
            AllCurrency("BRL", "Brazilian Real", "BRL - Brazilian Real"),
            AllCurrency("ZAR", "South African Rand", "ZAR - South African Rand"),
            AllCurrency("SGD", "Singapore Dollar", "SGD - Singapore Dollar"),
            AllCurrency("NZD", "New Zealand Dollar", "NZD - New Zealand Dollar"),
            AllCurrency("HKD", "Hong Kong Dollar", "HKD - Hong Kong Dollar"),
            AllCurrency("KRW", "South Korean Won", "KRW - South Korean Won"),
            AllCurrency("MXN", "Mexican Peso", "MXN - Mexican Peso"),
            AllCurrency("NOK", "Norwegian Krone", "NOK - Norwegian Krone"),
            AllCurrency("SEK", "Swedish Krona", "SEK - Swedish Krona"),
            AllCurrency("DKK", "Danish Krone", "DKK - Danish Krone"),
            AllCurrency("TRY", "Turkish Lira", "TRY - Turkish Lira"),
            AllCurrency("AED", "United Arab Emirates Dirham", "AED - United Arab Emirates Dirham"),
            AllCurrency("SAR", "Saudi Riyal", "SAR - Saudi Riyal"),
            AllCurrency("THB", "Thai Baht", "THB - Thai Baht"),
            AllCurrency("MYR", "Malaysian Ringgit", "MYR - Malaysian Ringgit"),
            AllCurrency("IDR", "Indonesian Rupiah", "IDR - Indonesian Rupiah"),
            AllCurrency("PHP", "Philippine Peso", "PHP - Philippine Peso"),
            AllCurrency("PLN", "Polish Zloty", "PLN - Polish Zloty"),
            AllCurrency("HUF", "Hungarian Forint", "HUF - Hungarian Forint"),
            AllCurrency("CZK", "Czech Koruna", "CZK - Czech Koruna"),
            AllCurrency("ILS", "Israeli Shekel", "ILS - Israeli Shekel"),
            AllCurrency("VND", "Vietnamese Dong", "VND - Vietnamese Dong"),
            AllCurrency("EGP", "Egyptian Pound", "EGP - Egyptian Pound"),
            AllCurrency("PKR", "Pakistani Rupee", "PKR - Pakistani Rupee"),
            AllCurrency("KWD", "Kuwaiti Dinar", "KWD - Kuwaiti Dinar"),
            AllCurrency("QAR", "Qatari Riyal", "QAR - Qatari Riyal"),
            AllCurrency("NGN", "Nigerian Naira", "NGN - Nigerian Naira"),
            AllCurrency("GHS", "Ghanaian Cedi", "GHS - Ghanaian Cedi"),
            AllCurrency("KES", "Kenyan Shilling", "KES - Kenyan Shilling"),
            AllCurrency("UGX", "Ugandan Shilling", "UGX - Ugandan Shilling"),
            AllCurrency("TZS", "Tanzanian Shilling", "TZS - Tanzanian Shilling"),
            AllCurrency("ZMW", "Zambian Kwacha", "ZMW - Zambian Kwacha"),
            AllCurrency("MAD", "Moroccan Dirham", "MAD - Moroccan Dirham"),
            AllCurrency("XOF", "West African CFA Franc", "XOF - West African CFA Franc"),
            AllCurrency("XAF", "Central African CFA Franc", "XAF - Central African CFA Franc"),
            AllCurrency("CLP", "Chilean Peso", "CLP - Chilean Peso"),
            AllCurrency("ARS", "Argentine Peso", "ARS - Argentine Peso"),
            AllCurrency("COP", "Colombian Peso", "COP - Colombian Peso"),
            AllCurrency("PEN", "Peruvian Sol", "PEN - Peruvian Sol"),
            AllCurrency("UYU", "Uruguayan Peso", "UYU - Uruguayan Peso")
        )


        // Create an instance of the custom adapter
        allCurrencyAdapter = AllCurrencyAdapter(requireContext(), allCurrencyList)
        currencyView.setAdapter(allCurrencyAdapter)

        currencyView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val currencyList =
                    adapterView?.getItemAtPosition(p2) as AllCurrency
                currency = currencyList.currencyName
                currencyView.setText(currency)
            }
        }

        currencyView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout8.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 5
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 4
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout8.visibility = View.GONE
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }

        })

        binding.emailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        binding.constraintLayout9.visibility = View.VISIBLE
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 6
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 5
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isNotEmpty()) {
//                        scroll.post {
//                            scroll.fullScroll(View.FOCUS_DOWN)
//                        }

                        // Update progress bar
                        answeredQuestions = 7
                        updateProgressBar()
                        wasEmpty = true
                    } else if (s.isEmpty()) {
                        answeredQuestions = 6
                        wasEmpty = true
                        updateProgressBar()
                        binding.constraintLayout9.visibility = View.GONE

                    }
                }
            }

        })
//
//        binding.continueBtn.setOnClickListener {
//            Log.d("ANSWERED_QUESTIONS", answeredQuestions.toString())
//            if (answeredQuestions==7){
//                binding.continueBtn.isEnabled = true
//            findNavController().navigate(R.id.action_registerFragment_to_incomeAndInflowFragment)
//            }else{
//                Toast.makeText(requireContext(), "Please answer all the questions", Toast.LENGTH_SHORT).show()
//            }
//        }

        binding.continueBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_incomeAndInflowFragment)
        }

        binding.dateOfBirth.setOnClickListener {
            showDateTimePicker()
        }
    }


//        binding.login.setOnClickListener {
//            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
//            findNavController().navigate(action)
//        }


    private fun showDateTimePicker() {
        val currentDate = Calendar.getInstance()
        date = Calendar.getInstance()
        DatePickerDialog(
            context!!,
            R.style.BlackDatePickerDialog,
            { view, year, monthOfYear, dayOfMonth ->
                date.set(year, monthOfYear, dayOfMonth)
                val month = monthOfYear + 1
                binding.dateOfBirth.setText("$dayOfMonth-$month-$year")
            }, currentDate[Calendar.YEAR], currentDate[Calendar.MONTH], currentDate[Calendar.DATE]
        ).show()
    }


    private fun updateProgressBar() {
        progressBar.progress = answeredQuestions
    }

    private fun countrySetUp() {
        val bindingInterface = object : GenericRecyclerBindingInterface<CountryModel> {
            override fun bindData(item: CountryModel, view: View) {
                val name: TextView = view.findViewById(R.id.country_name)
                val img: TextView = view.findViewById(R.id.country_flag)
                name.text = item.country_name
                img.setBackgroundResource(item.image)
            }
        }

//        val adapter = GenericRecyclerAdapter(countryList, R.layout.country_item_layout, bindingInterface, null)
//        binding.country.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.country.adapter = adapter
    }

    private fun countryList() {
        countryList = arrayListOf()
        countryList.add(CountryModel(R.drawable.monetization_on, "Nigeria"))
        countryList.add(CountryModel(R.drawable.monetization_on, "USA"))
        countryList.add(CountryModel(R.drawable.monetization_on, "Canada"))
        countryList.add(CountryModel(R.drawable.monetization_on, "France"))
        countryList.add(CountryModel(R.drawable.monetization_on, "Spain"))
        countryList.add(CountryModel(R.drawable.monetization_on, "England"))

    }

    private fun dateOfBirthDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            R.style.BlackDatePickerDialog,
            { _, selectedYear, selectedMonth, selectedDay ->
//                selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
//                dateTextView.text = selectedDate // Display selected date in the TextView
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}