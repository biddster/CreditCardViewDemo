package com.biddster.creditcardviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vinaygaba.creditcardview.CreditCardView;

public class CardListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        final ListView cardList = (ListView) findViewById(R.id.card_list);
        cardList.setAdapter(new CardListAdapter(this,
                new Card("1234123412341234", "card 1", "12/15"),
                new Card("4321432143214321", "card 2", "01/16"),
                new Card("5678567856785678", "card 3", "02/17"),
                new Card("8765876587658765", "card 4", "03/18")
        ));
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class Card {
        private final String cardNumber;
        private final String cardName;
        private final String expiry;

        public Card(final String cardName, final String cardNumber, final String expiry) {
            this.cardName = cardName;
            this.cardNumber = cardNumber;
            this.expiry = expiry;
        }

        public String getCardName() {
            return cardName;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getExpiry() {
            return expiry;
        }
    }

    private static class CardListAdapter extends ArrayAdapter<Card> {

        public CardListAdapter(final Context context, final Card... cards) {
            super(context, -1, cards);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_list_row, parent, false);
            }
            final Card card = getItem(position);
            final CreditCardView creditCardView = (CreditCardView) convertView.findViewById(R.id.card_list_row_credit_card);
            creditCardView.setCardNumber(card.getCardNumber());
            creditCardView.setCardName(card.getCardName());
            creditCardView.setExpiryDate(card.getExpiry());
            return convertView;
        }
    }
}
