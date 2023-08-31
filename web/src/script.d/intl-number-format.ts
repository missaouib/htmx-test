import {html, LitElement} from 'lit';

export class IntlNumberFormat extends LitElement {
    static properties = {
        value: {type: Number},
        locale: {type: String},
        options: {type: Object},
    };

    value!: number;
    locale?: string;
    options?: Intl.NumberFormatOptions;

    render() {
        const formatter = new Intl.NumberFormat(this.locale, this.options);
        const parts = formatter.formatToParts(this.value);
        return html`
          <data value="${this.value}">
            ${parts.map(part =>
                html`<span part="${part.type}">${part.value}</span>`)}
          </data>`;
    }
}

customElements.define('intl-number-format', IntlNumberFormat);
